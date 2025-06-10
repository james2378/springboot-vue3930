package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *最新电影：(TheLatestMovie)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TheLatestMovie")
public class TheLatestMovie implements Serializable {

    //TheLatestMovie编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "the_latest_movie_id")
    private Integer the_latest_movie_id;
    // 电影名称
    @Basic
    private String movie_title;
    // 电影编号
    @Basic
    private String movie_number;
    // 电影分类
    @Basic
    private String film_classification;
    // 场次
    @Basic
    private String number_of_sessions;
    // 封面
    @Basic
    private String cover;
    // 价格
    @Basic
    private String price;
    // 电影详情
    @Basic
    private String movie_details;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
