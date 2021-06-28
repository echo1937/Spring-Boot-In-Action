package com.example.validation.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    /**
     * 后端是否处理成功
     */
    private boolean success;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误消息
     */
    private String msg;
    /**
     * 给前端的返回值
     */
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result fail(ErrorCode errorCode) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(errorCode.getCode());
        result.setMsg(errorCode.getMsg());
        return result;
    }

    public static Result fail(ErrorCode errorCode, Object data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(errorCode.getCode());
        result.setMsg(errorCode.getMsg());
        result.setData(data);
        return result;
    }
}
