package com.example.validation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SYSTEM_ERROR("0000", "系统异常"),
    PARAM_ERROR("1000", "参数不正确");

    private final String code;
    private final String msg;
}
