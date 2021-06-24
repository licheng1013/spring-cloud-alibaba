package com.demo.exception;

/**
 * @author lc
 * @since 2021/6/23
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
