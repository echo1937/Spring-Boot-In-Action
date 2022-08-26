package com.example.springboottestjunit5.testcase;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

@Slf4j
class RepeatedUnitTest {

    @DisplayName("重复测试")
    @RepeatedTest(value = 3)
    void i_am_a_repeated_test() {
        log.info("执行测试");
    }

    @DisplayName("自定义名称重复测试")
    @RepeatedTest(value = 3, name = "{displayName} 第 {currentRepetition} 次")
    void i_am_a_repeated_test_2() {
        log.info("执行测试");
    }
}
