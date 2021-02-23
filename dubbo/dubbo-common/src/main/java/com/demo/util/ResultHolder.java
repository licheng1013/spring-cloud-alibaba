package com.demo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Result holder.
 *
 * @author zhangsen
 */
public class ResultHolder {

    private static final Map<String, String> cacheMap = new ConcurrentHashMap<String, String>();


    /**
     * @author lc
     * @date 2021/2/23
     * @description 设置
     */
    public static void set(String txId, String result){
        cacheMap.put(txId, result);
    }

    /**
     * @author lc
     * @date 2021/2/23
     * @description 获取
     */
    public static String get(String txId){
        return cacheMap.get(txId);
    }

    /**
     * @author lc
     * @date 2021/2/23
     * @description 删除
     */
    public static void remove(String txId){
        cacheMap.remove(txId);
    }

}