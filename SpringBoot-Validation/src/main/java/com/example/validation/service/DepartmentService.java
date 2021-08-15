package com.example.validation.service;

import com.example.validation.entity.Department;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class DepartmentService {
    public void add(@Valid Department department) {
        System.out.println("部门添加成功");
    }
}
