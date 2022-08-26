package com.example.springboottestjunit5.testcase;

import org.junit.jupiter.api.*;


@DisplayName("我的第一个测试用例")
public class MyFirstTestCaseTest {

    @BeforeAll
    public static void init() {
        System.out.println("初始化数据");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("清理数据");
    }

    @BeforeEach
    public void tearup() {
        System.out.println("当前测试方法开始");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("当前测试方法结束");
    }

    @DisplayName("我的第一个测试")
    @Test
    void testFirstTest() {
        System.out.println("我的第一个测试开始测试");
    }

    @DisplayName("我的第二个测试")
    @Test
    void testSecondTest() {
        System.out.println("我的第二个测试开始测试");
    }

    @DisplayName("我的第三个测试")
    @Disabled
    @Test
    void testThirdTest() {
        System.out.println("我的第三个测试开始测试");
    }

    @Test
    void testGroupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        Assertions.assertAll("numbers",
                () -> Assertions.assertEquals(1, numbers[1]),
                () -> Assertions.assertEquals(3, numbers[3]),
                () -> Assertions.assertEquals(4, numbers[4])
        );
    }

    @Test
    @DisplayName("测试捕获的异常")
    void assertThrowsException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> Integer.valueOf(null));
    }

}

