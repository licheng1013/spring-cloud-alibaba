package com.demo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;


/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Setter
@Getter
public class Goods extends Model<Goods> {
    /**
     * ${column.comment}
     */
    private Integer goodsId;
    /**
     * 商品信息
     */
    private String info;
    /**
     * 库存
     */
    private Integer total;
}