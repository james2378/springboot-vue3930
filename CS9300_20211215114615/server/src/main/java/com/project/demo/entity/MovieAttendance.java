package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *电影上座率：(MovieAttendance)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MovieAttendance")
public class MovieAttendance implements Serializable {

    //MovieAttendance编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_attendance_id")
    private Integer movie_attendance_id;
    // 电影名称
    @Basic
    private String movie_title;
    // 上座数
    @Basic
    private Integer number_of_seats;
    // 日期
    @Basic
    private Timestamp date;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
