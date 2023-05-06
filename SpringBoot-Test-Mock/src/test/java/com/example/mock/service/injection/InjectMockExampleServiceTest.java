package com.example.mock.service.injection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class InjectMockExampleServiceTest {

    @InjectMocks
    private InjectMockExampleService injectMockExampleService;

    @Mock
    private MockExampleService mockExampleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    // 使用Mockito模拟
    @Test
    void add() {
        // 模拟MockExampleService的add函数对于任何参数返回都为10
        when(mockExampleService.add(anyInt(), anyInt())).thenReturn(10);
        // @InjectMock会走真实的add方法，只不过mock会返回一个模拟的结果
        assertEquals(10, injectMockExampleService.add(1, 2));
    }
}