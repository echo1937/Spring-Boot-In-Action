package com.example.cache.service;

import com.example.cache.model.Employee;
import com.example.cache.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "EmployeeService", keyGenerator = "globalKeyGenerator")
public class EmployeeService {
    final EmployeeRepository employeeRepository;

    @Cacheable
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Cacheable
    public Employee getEmpByLastName(String lastName) {
        return employeeRepository.getEmployeeByLastName(lastName);
    }
}
