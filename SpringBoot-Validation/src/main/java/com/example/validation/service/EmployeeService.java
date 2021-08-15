package com.example.validation.service;

import com.example.validation.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService implements IEmployeeService {

    @Override
    public void add(Employee employee) {
        System.out.println("员工添加成功");
    }

    @Override
    public void addList(List<Employee> employeeList) {
        System.out.println("批量添加成功");
    }

    @Override
    public Employee getById(Integer id) {
        System.out.println("返回null以触发返回值的@NotNull注解");
        return null;
    }
}
