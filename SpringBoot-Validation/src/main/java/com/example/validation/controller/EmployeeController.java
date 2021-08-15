package com.example.validation.controller;


import com.example.validation.common.Result;
import com.example.validation.entity.Employee;
import com.example.validation.validation.annotation.ListGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

    /*校验入参*/
    @GetMapping
    public Result add(@RequestBody @Min(10) Integer id) {
        return Result.success();
    }

    /*校验入参: 实体类中做分组验证(Add和Default组)*/
    @PostMapping
    public Result add(@RequestBody @Validated(value = {Employee.Add.class, Default.class}) Employee employee) {
        return Result.success();
    }

    /*校验入参: 实体类中做分组验证(Update和Default组)*/
    @PutMapping
    public Result update(@RequestBody @Validated(value = {Employee.Update.class, Default.class}) Employee employee) {
        return Result.success();
    }

    /*校验入参: @ListGroup (List中做分组验证)*/
    @PostMapping("/list")
    public Result addList(@RequestBody @ListGroup(groupings = {Employee.Add.class, Default.class}, quickFail = true) List<Employee> employeeList) {
        return Result.success();
    }

}
