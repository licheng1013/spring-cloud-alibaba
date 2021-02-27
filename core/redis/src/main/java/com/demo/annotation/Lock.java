package com.demo.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lock {
    /** 锁前缀 **/
    String prefix() default "";
    /** 方法参数字段对应字段 **/
    String [] keys() default {};
    /** 默认RuntimeException异常 **/
    Class<? extends  Throwable> exception() default RuntimeException.class;
    /** 超时时间3秒 **/
    long timeout() default 3000;
    /** 报错信息 **/
    String msg()  default  "锁被占用";
    /** 是否手动释放锁,基于ThreadLocal,true开启手动释放锁,默认false自动释放锁,3秒超时 **/
    boolean isLock() default false;
}