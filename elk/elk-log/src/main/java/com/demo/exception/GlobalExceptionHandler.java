package com.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 *
 * @author lc
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理 ServiceException 异常
     * @since 2021/6/23
     */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<String> doHandleServiceException(ServiceException e) {
        return JsonResult.fail(e.getMessage());
    }

    /**
     * 处理 Exception 异常
     * @since 2021/6/23
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<String> doHandleServiceException(Exception e) {
        log.info("系统异常",e);
        return JsonResult.fail("系统繁忙");
    }
}