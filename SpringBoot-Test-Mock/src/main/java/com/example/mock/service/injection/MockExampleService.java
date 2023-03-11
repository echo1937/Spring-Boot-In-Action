package com.example.mock.service.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
class MockExampleService {

    int add(int a, int b) {
        return a + b;
    }

}

@Service
class InjectMockExampleService {

    @Autowired
    private MockExampleService mockExampleService;

    int add(int a, int b) {
        return mockExampleService.add(a, b);
    }

}