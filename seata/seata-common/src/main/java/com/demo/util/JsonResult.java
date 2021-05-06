package com.demo.util;

import lombok.Data;

/**
 * 泛型化
 * @author lc
 * @date 2020/11/30
 */
@Data
public class JsonResult<T> {
    private Object msg;
    private int code ;
    private T data;
    public static <T>JsonResult<T> okMsg(T msg){
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setMsg(msg);
        jsonResult.setCode(0);
        return jsonResult;
    }
    public static <T>JsonResult<T> okData(T data){
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setData(data);
        jsonResult.setCode(0);
        return jsonResult;
    }
    public static <T>JsonResult<T> fail(T msg){
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setMsg(msg);
        jsonResult.setCode(-1);
        return jsonResult;
    }
}