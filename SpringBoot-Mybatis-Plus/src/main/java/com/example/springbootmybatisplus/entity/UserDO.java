package com.example.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.springbootmybatisplus.pojo.SexEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "user", autoResultMap = true)
public class UserDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    @TableField(value = "nickname", condition = SqlCondition.EQUAL)
    private String nickname;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private SexEnum sex;

    /**
     * 爱好，JSON字段
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> hobbies;

    /**
     * 时间字段，自动添加
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 时间字段，自动添加/更新
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private Integer deleted;

}