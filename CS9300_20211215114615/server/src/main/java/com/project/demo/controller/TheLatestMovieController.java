package com.project.demo.controller;

import com.project.demo.entity.TheLatestMovie;
import com.project.demo.service.TheLatestMovieService;
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
 *最新电影：(TheLatestMovie)表控制层
 *
 */
@RestController
@RequestMapping("/the_latest_movie")
public class TheLatestMovieController extends BaseController<TheLatestMovie,TheLatestMovieService> {

    /**
     *最新电影对象
     */
    @Autowired
    public TheLatestMovieController(TheLatestMovieService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
