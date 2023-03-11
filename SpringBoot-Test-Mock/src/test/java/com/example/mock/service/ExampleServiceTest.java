package com.example.mock.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * 本例子中用的都是Junit5, 要谨防误用李鬼注解
 */
class ExampleServiceTest {
    @Spy
    private ExampleService spyExampleService;
    @Mock
    private ExampleService mockExampleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * 本例实际上只是演示了@Spy和@Mock的区别, 并没有实际意义, mockito大都是为了解决外部依赖的
     */

    // 测试 spy
    @Test
    void spy_add() {
        // 默认会走真实方法
        assertEquals(3, spyExampleService.add(1, 2));  // 此处add会真实执行, 有打印

        // 打桩后，不会走真实方法了
        when(spyExampleService.add(1, 2)).thenReturn(10); // 此处add会真实执行, 有打印
        assertEquals(10, spyExampleService.add(1, 2)); // 此处add不会真实执行, 没有打印
        assertEquals(10, spyExampleService.add(1, 2)); // 此处add不会真实执行, 没有打印 (说明stub一直有效)

        // add(2, 1)和add(1, 2)被认为是不同的调用
        // 但是参数不匹配的调用，依然走真实方法
        assertEquals(3, spyExampleService.add(2, 1));  // 此处add会真实执行, 有打印


        // doReturn和when在stub时有区别

        doReturn(10).when(spyExampleService).add(10, 20); // 此处add不会真实执行, 没有打印
        assertEquals(10, spyExampleService.add(10, 20));     // 此处add不会真实执行, 没有打印

        // 总结: @Spy下, when会真实执行mock对象的方法, doReturn不会真实执行mock对象的方法, 两者行为统称stub;
        //       被stub以后, 再次执行相同的调用就都不会真实执行了, 所以when和doReturn的差异指的是stub时的区别;

    }

    // 测试 mock
    @Test
    void mock_add() {

        // 默认返回结果是返回类型int的默认值
        assertEquals(0, mockExampleService.add(1, 2));

    }
}

