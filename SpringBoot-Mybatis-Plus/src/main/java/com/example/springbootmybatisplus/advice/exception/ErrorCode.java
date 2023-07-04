package com.example.springbootmybatisplus.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public enum ErrorCode {

    // 成功
    SUCCESS(200, "请求成功"),

    // 系统异常 10XXX
    SYSTEM_ERROR(10000, "系统错误，请稍后重试"),

    // 服务异常 20XXX
    SERVICE_ERROR(20000, "服务错误, 请稍后重试"),

    // HTTP请求 201XX
    METHOD_NOT_SUPPORTED(20101, "不支持该请求类型"),
    VALIDATION_ERROR(20102, "请求参数校验错误"),
    RESPONSE_ENHANCE_ERROR(20103, "响应增强错误"),

    // 数据库操作 202XX
    DB_OPERATE_ERROR(20200, "数据库操作失败"),

    // Quartz错误 203XX
    CRON_EXPRESSION_INVALID(20301, "无效的CRON表达式"),
    START_TIME_GT_END_TIME(20302, "起始时间不能大于结束时间"),

    // 权限验证相关 30XXX
    AUTHENTICATION_ERROR(30001, "用户认证失败"),
    PERMISSION_DENIED(30002, "请求权限不足"),

    // 未知错误
    UNKNOWN_ERROR(40000, "未知错误");

    private final int code;
    private final String description;
}
