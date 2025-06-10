package com.project.demo.controller;

import com.project.demo.entity.SessionManagement;
import com.project.demo.service.SessionManagementService;
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
 *场次管理：(SessionManagement)表控制层
 *
 */
@RestController
@RequestMapping("/session_management")
public class SessionManagementController extends BaseController<SessionManagement,SessionManagementService> {

    /**
     *场次管理对象
     */
    @Autowired
    public SessionManagementController(SessionManagementService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapsession_number = new HashMap<>();
        mapsession_number.put("session_number",String.valueOf(paramMap.get("session_number")));
        List listsession_number = service.select(mapsession_number, new HashMap<>()).getResultList();
        if (listsession_number.size()>0){
            return error(30000, "字段场次编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
