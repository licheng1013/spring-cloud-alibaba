package com.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * @author lc
 * @date 2020-12-31
 * @description
 */
@Setter
@Getter
public class    User extends Model<User> {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}