package com.demo.exception;

/**
 * @author licheng
 * @date   2020-5-10
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }
    
    public ServiceException(String message) {
        super(message);
    }
}
