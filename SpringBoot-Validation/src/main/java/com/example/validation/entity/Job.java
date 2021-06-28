package com.example.validation.entity;


import com.example.validation.validation.annotation.MultipleOfThree;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class Job {

    /**
     * 1、validation的注解一般不会管 null 的情况（null 这种情况, 由@NotNull 负责
     * <p>
     * 2、同一个注解，可以对多种参数类型生效
     * <p>
     * <p>
     * 加入现在有这么一个场景
     * <p>
     * 1、对于 Integer 而言，必须是 3 的倍数
     * <p>
     * 2、对于 List 而言，list 中的元素个数，必须是 3 的倍数
     * <p>
     * <p>
     * 准备写一个注解，去实现  “3 的倍数“ 的验证
     * 这个注解支持两种数据类型 Integer、List
     */

    @MultipleOfThree
    private Integer id;

    @Size(min = 1)
    private String name;

    @Size(min = 1, max = 10)
    @NotNull
    @MultipleOfThree
    private List<@Size(min = 1) String> labels;
}
