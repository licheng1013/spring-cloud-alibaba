package com.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Setter
@Getter
@ToString
public class Order extends Model<Order> {
    /**
     * ${column.comment}
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    /**
     * 订单金额
     */
    private Integer money;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 订单数量
     */
    private Integer amount;
    /**
     * 订单描述
     */
    private String description;
    /** 创建时间 **/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}