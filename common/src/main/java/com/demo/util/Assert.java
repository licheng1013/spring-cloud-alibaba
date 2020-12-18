package com.demo.util;

import com.demo.exception.ServiceException;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 15:23
 */
public class Assert {
    public static void isNull(Object obj,String msg) {
        if (obj == null) {
            throw new ServiceException(msg);
        }
    }

    public static void exception(String msg){
        throw new ServiceException(msg);
    }
}
