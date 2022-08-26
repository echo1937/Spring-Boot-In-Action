package com.example.springboottestjunit5.testcase;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@Slf4j
class AppTest {

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("execute testSingleSuccessTest()");
    }


    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }

    @Test
    void testLambdaExpressions() {
        log.info("testLambdaExpressions()");
        assertTrue(Stream.of(1, 2, 3).mapToInt(i -> i).sum() > 5, () -> "如果断言不正确你就会看到我");
    }

    @Test
    void groupAssertions() {
        log.info("execute groupAssertions()");
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(0, numbers[0]),
                () -> assertEquals(3, numbers[3]),
                () -> assertEquals(4, numbers[4])
        );
    }

    @Test
    void trueAssumption() {
        log.info("execute trueAssumption()");
        assumeTrue(5 > 1);
        assertEquals(7, 5 + 2);
    }

    @Test
    void falseAssumption() {
        log.info("execute falseAssumption()");
        assumeFalse(5 < 1);
        assertEquals(7, 5 + 2);
    }

    @Test
    void assumptionThat() {
        log.info("execute assumptionThat()");
        assumingThat("Just".contains("J"), () -> assertEquals(4, 2 + 2));
    }

    @Test
    void shouldThrowException() {
        log.info("execute shouldThrowException()");
        Throwable exception = assertThrows(UnsupportedOperationException.class,
                () -> {
                    throw new UnsupportedOperationException("Not supported");
                });
        assertEquals(exception.getMessage(), "Not supported");
    }

    @Test
    void assertThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Integer.valueOf(null));
    }
}