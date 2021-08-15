package com.example.cache.service;

import com.example.cache.model.Department;
import com.example.cache.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "DepartmentService", keyGenerator = "globalKeyGenerator")
public class DepartmentService {
    final DepartmentRepository departmentRepository;

    @Cacheable
    public Department getDept(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
