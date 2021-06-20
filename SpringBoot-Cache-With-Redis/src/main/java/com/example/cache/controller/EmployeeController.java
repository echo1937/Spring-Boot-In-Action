package com.example.cache.controller;


import com.example.cache.model.Employee;
import com.example.cache.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    final EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/emp")
    public Employee getEmpByLastName(@RequestParam("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }

}
