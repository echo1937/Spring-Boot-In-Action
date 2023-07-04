package com.example.springbootmybatisplus.advice;


import com.example.springbootmybatisplus.advice.exception.ErrorCode;
import com.example.springbootmybatisplus.advice.exception.ServiceException;
import com.example.springbootmybatisplus.advice.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ResponseStatus(INTERNAL_SERVER_ERROR)
@RestControllerAdvice(basePackages = "com.dnt.portal.controller")
public class GlobalExceptionAdvice {

    /**
     * 禁止访问
     */
//    @ResponseStatus(UNAUTHORIZED)
//    @ExceptionHandler(AccessDeniedException.class)
//    public <T> ResponseResult<T> accessDeniedException(AccessDeniedException e) {
//        log.error("禁止访问", e);
//        return ResponseResult.errorResult(ErrorCode.NO_PERMISSION);
//    }

    /**
     * 不支持的请求类型
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> ResponseResult<T> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持的请求类型", e);
        return ResponseResult.errorResult(ErrorCode.METHOD_NOT_SUPPORTED);
    }

    /**
     * RequestParam参数校验异常
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public <T> ResponseResult<T> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("@RequestParam指定的参数缺失", e);
        return ResponseResult.errorResult(ErrorCode.VALIDATION_ERROR);
    }

    /**
     * 参数校验异常
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> methodArgumentNotValidException(MethodArgumentNotValidException validException) {
        log.error("参数校验异常", validException);
        String collect = validException.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return ResponseResult.errorResult(ErrorCode.VALIDATION_ERROR, collect);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<String> httpMessageNotReadableException(HttpMessageNotReadableException readableException) {
        log.error("传入的参数未通过枚举校验", readableException);
        return ResponseResult.errorResult(ErrorCode.VALIDATION_ERROR, "传入的参数未通过枚举校验: " + readableException.getCause().getMessage());
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = {SystemException.class, ServiceException.class})
    public <T> ResponseResult<T> customException(Exception exception) {
        log.error("程序运行异常", exception);
        ErrorCode errorCode;
        if (exception instanceof SystemException systemException) {
            errorCode = systemException.getErrorCode();
        } else if (exception instanceof ServiceException serviceException) {
            errorCode = serviceException.getErrorCode();
        } else {
            errorCode = ErrorCode.SUCCESS;
        }
        return ResponseResult.errorResult(errorCode);
    }

    /**
     * 处理未捕获的RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public <T> ResponseResult<T> handleRuntimeException(RuntimeException e) {
        log.error("未捕获的RuntimeException", e);
        return ResponseResult.errorResult(ErrorCode.SYSTEM_ERROR);
    }


    /**
     * 处理未捕获的Exception
     */
    @ExceptionHandler(Exception.class)
    public <T> ResponseResult<T> handleException(Exception e) {
        log.error("未捕获的Exception", e);
        return ResponseResult.errorResult(ErrorCode.SYSTEM_ERROR);
    }


}