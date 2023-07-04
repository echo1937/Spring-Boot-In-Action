package com.example.springbootmybatisplus.advice.exception;



import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class SystemException extends RuntimeException {
    private final ErrorCode errorCode;

    public SystemException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
