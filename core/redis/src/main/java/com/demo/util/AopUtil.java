package com.demo.util;

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
}
