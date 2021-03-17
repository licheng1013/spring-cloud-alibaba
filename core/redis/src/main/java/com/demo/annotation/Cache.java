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
     * @return 默认起始随机时间
     */
    long statTime() default 0;

    /**
     * @return 默认结束随机时间
     */
    long endTime() default 0;
}
