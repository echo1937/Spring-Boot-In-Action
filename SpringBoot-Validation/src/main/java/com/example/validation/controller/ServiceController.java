package com.example.validation.controller;

import com.example.validation.common.Result;
import com.example.validation.entity.Department;
import com.example.validation.entity.Employee;
import com.example.validation.service.DepartmentService;
import com.example.validation.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Service层做参数验证, 抛出的异常为javax.validation.ConstraintViolationException
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceController {

    final DepartmentService departmentService;
    final EmployeeService employeeService;

    @PostMapping("/department")
    public Result addDepartment(@RequestBody Department department) {
        departmentService.add(department);
        return Result.success();
    }

    @PostMapping("/employee")
    public Result addEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
        return Result.success();
    }

    @PostMapping("/employeeList")
    public Result addEmployee(@RequestBody List<Employee> employeeList) {
        employeeService.addList(employeeList);
        return Result.success();
    }

    @GetMapping("/employee")
    public Result getEmployee(@RequestParam Integer id) {
        Employee byId = employeeService.getById(id);
        return Result.success(byId);
    }

}
