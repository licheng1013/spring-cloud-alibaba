package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
}