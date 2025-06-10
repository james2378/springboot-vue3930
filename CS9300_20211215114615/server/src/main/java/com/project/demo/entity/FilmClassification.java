package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *电影分类：(FilmClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FilmClassification")
public class FilmClassification implements Serializable {

    //FilmClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_classification_id")
    private Integer film_classification_id;
    // 分类名称
    @Basic
    private String classification_name;
    // 分类编号
    @Basic
    private String classification_number;
    // 分类图片
    @Basic
    private String classified_picture;
    // 分类描述
    @Basic
    private String classification_description;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
