package com.project.demo.controller;

import com.project.demo.entity.FilmClassification;
import com.project.demo.service.FilmClassificationService;
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
 *电影分类：(FilmClassification)表控制层
 *
 */
@RestController
@RequestMapping("/film_classification")
public class FilmClassificationController extends BaseController<FilmClassification,FilmClassificationService> {

    /**
     *电影分类对象
     */
    @Autowired
    public FilmClassificationController(FilmClassificationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapclassification_number = new HashMap<>();
        mapclassification_number.put("classification_number",String.valueOf(paramMap.get("classification_number")));
        List listclassification_number = service.select(mapclassification_number, new HashMap<>()).getResultList();
        if (listclassification_number.size()>0){
            return error(30000, "字段分类编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
