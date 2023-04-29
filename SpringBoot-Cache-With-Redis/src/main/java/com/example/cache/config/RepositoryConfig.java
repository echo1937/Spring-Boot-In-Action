package com.example.cache.config;

import com.example.cache.model.Department;
import com.example.cache.model.Employee;
import com.example.cache.repository.DepartmentRepository;
import com.example.cache.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RepositoryConfig {
    final EmployeeRepository employeeRepository;
    final DepartmentRepository departmentRepository;

    // 初始化H2数据库
    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            Department department = new Department(1L, "技术部");
            List<Employee> employees = Arrays.asList(
                    new Employee(1L, "张三", "zs@tech.com", 0, department),
                    new Employee(2L, "李四", "ls@tech.com", 0, department));
            departmentRepository.save(department);
            employeeRepository.saveAll(employees);
            log.info("H2数据库初始化完毕");
        };
    }


}
