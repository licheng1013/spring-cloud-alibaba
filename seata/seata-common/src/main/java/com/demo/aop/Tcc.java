package com.demo.aop;

import java.lang.annotation.*;

/**
 * @author root
 * @description TODO
 * @date 2021/3/19 16:45
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Tcc {
    /**
     * @return 配置指定前缀key进行保存.... try,commit,rollback方法必须注解前缀必须一致
     */
    String prefix() default "";

    /**
     * @return true表示一阶段,false表示二阶段,空回滚..回收redis
     */
    boolean type() default true;
}
