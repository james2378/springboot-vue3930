package com.project.demo.controller;

import com.project.demo.entity.Film;
import com.project.demo.service.FilmService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *电影：(Film)表控制层
 *
 */
@RestController
@RequestMapping("/film")
public class FilmController extends BaseController<Film,FilmService> {

    /**
     *电影对象
     */
    @Autowired
    public FilmController(FilmService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapmovie_number = new HashMap<>();
        mapmovie_number.put("movie_number",String.valueOf(paramMap.get("movie_number")));
        List listmovie_number = service.select(mapmovie_number, new HashMap<>()).getResultList();
        if (listmovie_number.size()>0){
            return error(30000, "字段电影编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
