package com.example.asynchronous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AsynchronousExecutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsynchronousExecutionApplication.class, args);
    }

}
