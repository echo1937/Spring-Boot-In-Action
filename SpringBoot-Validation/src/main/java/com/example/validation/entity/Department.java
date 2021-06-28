package com.example.validation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Department {
    /**
     * 主键
     */
    @Null(message = "主键不可以有值")
    private Integer id;
    /**
     * 父级 ID
     */
    @NotNull
    private Integer parentId;
    /**
     * 部门名称
     */
    @NotBlank
    private String name;
    /**
     * 成立时间
     */
    @NotNull
    @PastOrPresent
    private LocalDateTime createTime;
    /**
     * 员工列表
     */
    private List<@Valid Employee> employeeList;
}
