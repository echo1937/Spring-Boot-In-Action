package com.example.springbootmybatisplus.advice;


import com.example.springbootmybatisplus.advice.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  // 序列化时，添加所有不为NULL的字段
@Schema(name = "ResponseResult", description = "通用返回对象")
public class ResponseResult<T> {
    private Integer code;
    private String description;
    private T data;

    private ResponseResult() {
        this.code = ErrorCode.SUCCESS.getCode();
        this.description = ErrorCode.SUCCESS.getDescription();
    }

    private ResponseResult(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private ResponseResult(Integer code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public static <T> ResponseResult<T> okResult() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> okResult(T data) {
        return new ResponseResult<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription(), data);
    }

    public static <T> ResponseResult<T> errorResult(ErrorCode errorCode) {
        return new ResponseResult<>(errorCode.getCode(), errorCode.getDescription());
    }

    public static <T> ResponseResult<T> errorResult(ErrorCode errorCode, T data) {
        return new ResponseResult<>(errorCode.getCode(), errorCode.getDescription(), data);
    }

    public static <T> ResponseResult<T> errorResult(Integer code, String description) {
        return new ResponseResult<>(code, description);
    }

}
