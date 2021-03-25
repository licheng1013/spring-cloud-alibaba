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
public class User extends Model<User> {
    /**
     * ${column.comment}
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;
    /**
     * ${column.comment}
     */
    private Integer money;
    /** 冻结金额 **/
    private Integer freeze;
    /** 乐观锁 **/
    @Version
    private Integer version;
    /** 创建时间 **/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}