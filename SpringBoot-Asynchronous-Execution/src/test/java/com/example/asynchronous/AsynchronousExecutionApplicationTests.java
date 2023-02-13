package com.example.asynchronous;

import com.example.asynchronous.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
class AsynchronousExecutionApplicationTests {

    @Resource
    AsyncService asyncAnnotationExample;

    @Test
    void asyncMethodWithVoidReturnType() throws InterruptedException {
        log.info("Invoking an asynchronous with void return type method. " + Thread.currentThread().getName());
        asyncAnnotationExample.asyncMethodWithVoidReturnType();

        Thread.sleep(1000);
    }

    @Test
    void asyncMethodsWithReturnType() throws InterruptedException, ExecutionException {
        log.info("Invoking an asynchronous with return type method. " + Thread.currentThread().getName());
        Future<String> future = asyncAnnotationExample.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                log.info("Result from asynchronous process - " + future.get());
                break;
            }
            log.info("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

}
