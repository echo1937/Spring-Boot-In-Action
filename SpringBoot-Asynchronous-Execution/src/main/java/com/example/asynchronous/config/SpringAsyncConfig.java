package com.example.asynchronous.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Slf4j
@Configuration
@EnableAsync
@ComponentScan("com.example.asynchronous.service")
public class SpringAsyncConfig implements AsyncConfigurer {

    /*
        1. 我们可以通过在application.properties进行Task Execution相关的配置
            spring.task.execution.pool.max-size=16
            spring.task.execution.pool.queue-capacity=100
            spring.task.execution.pool.keep-alive=10s
        然后通过注入org.springframework.boot.autoconfigure.task.TaskExecutionProperties进行初始化配置,

            全部配置 =
                application.properties的显式配置
                + 未被覆盖的TaskExecutionProperties默认属性
                + application.properties不支持的配置(比如线程池的拒绝策略)

        2. org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration.taskExecutorBuilder()描述了
        如何通过TaskExecutionProperties来初始化ThreadPoolTaskExecutor

        see also: https://www.cnblogs.com/echo1937/p/17523127.html

     */

    /**
     * @return 这个线程池可以通过依赖注入进行调用
     */
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("method_level_");
        return threadPoolTaskExecutor;
    }

    /**
     * @return 这个bean没有办法通过依赖注入调用, 只能通过@Async使用
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("app_level_");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.info("Exception message - " + ex.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : params) {
                log.info("Parameter value - " + param);
            }
        };
    }
}
