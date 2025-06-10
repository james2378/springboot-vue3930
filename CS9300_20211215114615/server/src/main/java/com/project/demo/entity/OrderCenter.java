package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *订单中心：(OrderCenter)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OrderCenter")
public class OrderCenter implements Serializable {

    //OrderCenter编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_center_id")
    private Integer order_center_id;
    // 订单编号
    @Basic
    private String order_number;
    // 场次
    @Basic
    private String number_of_sessions;
    // 价格
    @Basic
    private String price;
    // 电影编号
    @Basic
    private String movie_number;
    // 电影名称
    @Basic
    private String movie_title;
    // 购买数
    @Basic
    private Integer number_of_purchases;
    // 合计
    @Basic
    private String total;
    // 购买人
    @Basic
    private Integer purchaser;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;
	// 选座状态
	@Basic
	private String seat;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
