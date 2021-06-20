package com.example.cache.controller;


import com.example.cache.model.Department;
import com.example.cache.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeptController {
    final DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Long id) {
        return departmentService.getDept(id);
    }
}
