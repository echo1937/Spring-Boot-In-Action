package com.example.validation.controller;


import com.example.validation.entity.Department;
import com.example.validation.common.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated  //对本类中的方法开启参数验证功能
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @PostMapping
    public Result add(@RequestBody @Valid /*校验后面的参数*/ Department department) {
        System.out.println(department);
        return Result.success();
    }

}
