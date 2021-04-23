package com.demo.annotation;

import java.lang.annotation.*;

/**
 * 统计api的请求次数日志
 * @author root
 * @description TODO
 * @date 2021/4/23 15:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiTotal {

}
