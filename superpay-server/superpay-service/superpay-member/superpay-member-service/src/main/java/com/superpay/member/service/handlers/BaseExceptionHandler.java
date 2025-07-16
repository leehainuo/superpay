package com.superpay.member.service.handlers;

import com.superpay.common.Response;
import com.superpay.common.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lihainuo
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public Response<Object> handleException(BusinessException e) {
        log.error("系统异常", e);
        return Response.error(e.getResponse());
    }

    @ExceptionHandler
    public Response<Object> handleException(Exception e) {
        log.error("系统异常", e);
        return Response.error(e.getMessage());
    }
}
