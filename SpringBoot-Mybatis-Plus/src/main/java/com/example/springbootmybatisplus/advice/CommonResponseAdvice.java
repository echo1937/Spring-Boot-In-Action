package com.example.springbootmybatisplus.advice;


import com.example.springbootmybatisplus.advice.exception.ErrorCode;
import com.example.springbootmybatisplus.advice.exception.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.example.springbootmybatisplus.controller")
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 1. 该注解存在在方法上
        boolean annotationClassPresent = returnType.getContainingClass().isAnnotationPresent(IgnoreResponseAdvice.class);
        // 2. 该注解存在在类上
        boolean annotationMethodPresent = Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class);
        // 3. 返回体已经是ResponseResult类型
        boolean alreadyEnhance = returnType.getParameterType().isAssignableFrom(ResponseResult.class);
        // 若为以上情况，则不进行响应体封装
        return !(annotationClassPresent || annotationMethodPresent || alreadyEnhance);
    }

    /**
     * 响应体增强
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型需要特别处理，否则会发生类型转换异常
        if (body instanceof String) {
            // 设置响应头
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            try {
                return this.objectMapper.writeValueAsString(ResponseResult.okResult(body));
            } catch (JsonProcessingException exception) {
                log.error("响应增强异常", exception);
                throw new SystemException(ErrorCode.RESPONSE_ENHANCE_ERROR);
            }
        }
        // 其他类型则不会发生异常，直接返回
        return ResponseResult.okResult(body);
    }
}
