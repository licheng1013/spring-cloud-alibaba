package com.demo.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * UserEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@FluentMybatis(table = "t_user")
public class User extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId("user_id")
  private Integer userId;

  /**
   * 创建时间
   */
  @TableField("create_time")
  private Date createTime;

  /**
   * 冻结金额
   */
  @TableField("freeze")
  private Integer freeze;

  /**
   * 金额
   */
  @TableField("money")
  private Integer money;

  /**
   * 乐观锁
   */
  @TableField("version")
  private Integer version;

  @Override
  public Serializable findPk() {
    return this.userId;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return User.class;
  }
}
