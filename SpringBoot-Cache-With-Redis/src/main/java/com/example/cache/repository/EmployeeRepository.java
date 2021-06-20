package com.example.cache.repository;

import com.example.cache.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getEmployeeByLastName(String lastName);
}
