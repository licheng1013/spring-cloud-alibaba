package com.demo.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author root
 * @description TODO
 * @date 2021/2/27 17:21
 */
public class AopUtil {
    public static Map<String,Object> getMap(Object[] args, Parameter[] parameters){
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i <  parameters.length; i++) {
            map.put(parameters[i].getName(), args[i]);
        }
        return map;
    }


    /**
     * @param joinPoint 对象体
     * @param cls 获取主键
     * @param <T> 泛型返回
     * @return 该注解
     */
    public static <T extends Annotation> T invoke(ProceedingJoinPoint joinPoint, Class<T> cls){
        Signature signature = joinPoint.getSignature(); //方法签名
        MethodSignature methodSignature = (MethodSignature) signature;//强转
        Method method = methodSignature.getMethod(); //获取的方法对象
        Annotation annotation = method.getAnnotation(cls);//获取方法上的注解
        if (annotation == null) {
            return null;
        }
        return (T)annotation;
    }
}
