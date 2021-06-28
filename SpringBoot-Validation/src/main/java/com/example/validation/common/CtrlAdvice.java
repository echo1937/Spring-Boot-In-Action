package com.example.validation.common;


import com.example.validation.validation.exception.ListGroupValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
// (basePackages = "com.ph.share.share.validator.controller")
@ResponseBody
public class CtrlAdvice {

    @ExceptionHandler // Controller层校验的异常处理
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        HashMap<Object, Object> collect = e.getBindingResult().getFieldErrors().stream()
                .collect(HashMap::new,
                        (hashMap, fieldError) -> hashMap.put(fieldError.getField(), fieldError.getDefaultMessage()),
                        HashMap::putAll);
        // https://stackoverflow.com/questions/24630963/nullpointerexception-in-collectors-tomap-with-null-entry-values
        return Result.fail(ErrorCode.PARAM_ERROR, collect);
    }

    @ExceptionHandler // Service层校验的异常处理
    public Result exceptionHandler(ConstraintViolationException e) {
        Map<Object, String> collect = e.getConstraintViolations().stream().collect(Collectors.toMap(
                violation -> violation.getRootBeanClass().getName() + " " + violation.getPropertyPath(),
                ConstraintViolation::getMessage));
        return Result.fail(ErrorCode.PARAM_ERROR, collect);
    }

    @ExceptionHandler // 通用ValidationException的异常处理(ListValidException)
    public Result exceptionHandler(ValidationException e) {
        if (e.getCause() instanceof ListGroupValidationException) {
            // 原来的Map
            Map<Integer, Set<ConstraintViolation<Object>>> errors = ((ListGroupValidationException) e.getCause()).getErrors();
            // 处理后的Map
            Map<Integer, Map<Path, String>> collect = errors.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                    entry -> entry.getValue().stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage))));
            return Result.fail(ErrorCode.PARAM_ERROR, collect);
        }
        return Result.fail(ErrorCode.PARAM_ERROR);
    }

    @ExceptionHandler // 最终Exception的异常处理
    public Result exceptionHandler(Exception e) {
        return Result.fail(ErrorCode.SYSTEM_ERROR);
    }
}
