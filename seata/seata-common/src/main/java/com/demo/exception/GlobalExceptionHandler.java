package com.demo.exception;

import com.demo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理,这里需要所有的feign调用返回格式一致!
 * @author lc
 * @date 2020/12/18
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * @author lc
   * @date 2020/12/18
   * @description 自定义业务异常处理
   */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<String> doHandleServiceException(ServiceException e){
        return JsonResult.fail(e.getMessage());
    }

    /**
     * @author lc
     * @date 2020/12/18
     * @description 系统异常处理
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<String> doHandleServiceException(Exception e){
        e.printStackTrace();
        return JsonResult.fail("系统繁忙");
    }
}