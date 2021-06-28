package com.example.validation.entity;

import com.example.validation.validation.sequenceprovider.EmployeeGroupSequenceProvider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@GroupSequenceProvider(EmployeeGroupSequenceProvider.class)
public class Employee {

    public interface Add {
    }

    public interface Update {
    }


    /**
     * 如果指定了验证组，那么该参数属于 特定组
     * <p>
     * 如果未指定验证组，那么该参数属于 默认组
     */
    @Null(groups = Add.class)          //在添加的时候生效
    @NotNull(groups = Update.class)    //在修改的时候生效
    private Integer id;
    @NotEmpty
    private String name;
    @Valid
    private Department department;


    /**
     * 举例
     * <p>
     * 1、员工的 age 在 20-25 之间，title 必须以 "初级" 开头
     * <p>
     * 2、员工的 age 在 25-30 之间，title 必须以 "中级" 开头
     * <p>
     * 3、否则，不做验证
     */
    public interface TitleJunior {
    }

    public interface TitleMiddle {
    }

    @NotNull
    private Integer age;

    @NotEmpty
    @Pattern(regexp = "^\u521d\u7ea7.*", groups = TitleJunior.class, message = "必须以初级开头")        // 初级
    @Pattern(regexp = "^\u4e2d\u7ea7.*", groups = TitleMiddle.class, message = "必须以中级开头")        // 中级
    private String title;

}
