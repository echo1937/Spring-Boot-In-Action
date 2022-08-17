package com.example.springbootmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MAN(1, "男"),
    WOMAN(2, "女");

    @EnumValue
    private final Integer code;
    private final String description;

    SexEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

}