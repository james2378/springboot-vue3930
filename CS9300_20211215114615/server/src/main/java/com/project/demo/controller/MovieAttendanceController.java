package com.project.demo.controller;

import com.project.demo.entity.MovieAttendance;
import com.project.demo.service.MovieAttendanceService;
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
 *电影上座率：(MovieAttendance)表控制层
 *
 */
@RestController
@RequestMapping("/movie_attendance")
public class MovieAttendanceController extends BaseController<MovieAttendance,MovieAttendanceService> {

    /**
     *电影上座率对象
     */
    @Autowired
    public MovieAttendanceController(MovieAttendanceService service) {
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
