package com.example.event.asynchronous;


import com.example.event.synchronous.CustomSpringEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * 通过指定AsynchronousSpringEventsConfig,实现了异步事件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AsynchronousSpringEventsConfig.class}, loader = AnnotationConfigContextLoader.class)
public class AsynchronousCustomSpringEventsIntegrationTest {

    @Autowired
    private CustomSpringEventPublisher publisher;

    @Test
    public void testCustomSpringEvents() throws InterruptedException {
        publisher.publishCustomEvent("Hello world!!");
        System.out.println("Done publishing asynchronous custom event. ");
    }
}
