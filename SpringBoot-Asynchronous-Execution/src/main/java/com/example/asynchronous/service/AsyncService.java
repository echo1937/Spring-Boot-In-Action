package com.example.asynchronous.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncService {

    private static final String Thread_Pool_Task_Executor = "threadPoolTaskExecutor";

    @Async(Thread_Pool_Task_Executor)
    public void asyncMethodWithVoidReturnType() {
        log.info("Execute method asynchronously. " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        log.info("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>("hello world !!!!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return null;
    }
}
