package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *场次管理：(SessionManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SessionManagement")
public class SessionManagement implements Serializable {

    //SessionManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_management_id")
    private Integer session_management_id;
    // 场次名称
    @Basic
    private String venue_name;
    // 场次编号
    @Basic
    private String session_number;
    // 场次详情
    @Basic
    private String session_details;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
