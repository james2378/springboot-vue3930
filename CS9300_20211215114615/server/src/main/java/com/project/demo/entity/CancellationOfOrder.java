package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *取消订单：(CancellationOfOrder)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CancellationOfOrder")
public class CancellationOfOrder implements Serializable {

    //CancellationOfOrder编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancellation_of_order_id")
    private Integer cancellation_of_order_id;
    // 订单编号
    @Basic
    private String order_number;
    // 电影名称
    @Basic
    private String movie_title;
    // 场次
    @Basic
    private String number_of_sessions;
    // 购买数
    @Basic
    private String number_of_purchases;
    // 合计
    @Basic
    private String total;
    // 购买人
    @Basic
    private Integer purchaser;
    // 支付情况
    @Basic
    private String payment_status;
    // 联系电话
    @Basic
    private String contact_number;
    // 取消原因
    @Basic
    private String cancellation_reason;
    // 审核状态
    @Basic
    private String examine_state;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
