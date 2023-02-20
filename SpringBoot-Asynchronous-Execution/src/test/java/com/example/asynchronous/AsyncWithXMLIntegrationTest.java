package com.example.asynchronous;

import com.example.asynchronous.service.AsyncComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:springAsync-config.xml")
public class AsyncWithXMLIntegrationTest {

    @Autowired
    private AsyncComponent asyncAnnotationExample;

    // tests

    @Test
    public void testAsyncAnnotationForMethodsWithVoidReturnType() throws InterruptedException {
        System.out.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        asyncAnnotationExample.asyncMethodWithVoidReturnType();
        Thread.sleep(2000);
        System.out.println("End - invoking an asynchronous method. ");
    }

}
