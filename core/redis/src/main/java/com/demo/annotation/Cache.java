package com.demo.annotation;

import java.lang.annotation.*;

/**
 * @author root
 * @description TODO
 * @date 2021/3/17 10:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    /**
     * @return 默认起始随机时间,单位(秒)
     */
    long minTime() default 30;

    /**
     * @return 默认结束随机时间,单位(秒),时间必须大于起始时间,否则以起始时间为随机数即1-30秒
     */
    long maxTime() default 60;
}
