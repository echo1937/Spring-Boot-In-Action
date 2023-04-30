package com.example.event.synchronous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {

    /**
     * Alternatively, the publisher class can implement the ApplicationEventPublisherAware interface, and this will also inject the event publisher on the application startup. Usually, it's simpler to just inject the publisher with @Autowire.
     * <p>As of Spring Framework 4.2, the ApplicationEventPublisher interface provides a new overload for the publishEvent(Object event) method that accepts any object as the event. Therefore, Spring events no longer need to extend the ApplicationEvent class.
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final String message) {
        System.out.println("Publishing custom event. ");
        /*
            有2个Listener监听了CustomSpringEvent: CustomSpringEventListener, AnnotationDrivenEventListener.handleCustom()
         */
        final CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        // default void publishEvent(ApplicationEvent event)
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    /**
     * 被AnnotationDrivenEventListener.handleSuccessful(final GenericSpringEvent<String> event)监听
     */
    public void publishGenericEvent(final String message, boolean success) {
        System.out.println("Publishing generic event.");
        final GenericSpringEvent<String> genericSpringEvent = new GenericStringSpringEvent(message, success);
        // void publishEvent(Object event)
        applicationEventPublisher.publishEvent(genericSpringEvent);
    }

    /**
     * 被GenericSpringEventListener监听
     */
    public void publishGenericAppEvent(final String message) {
        System.out.println("Publishing generic event.");
        final GenericSpringAppEvent<String> genericSpringEvent = new GenericStringSpringAppEvent(this, message);
        // default void publishEvent(ApplicationEvent event)
        applicationEventPublisher.publishEvent(genericSpringEvent);
    }

}

/**
 * The event class should extend ApplicationEvent if we're using versions before Spring Framework 4.2. As of the 4.2 version, the event classes no longer need to extend the ApplicationEvent class.
 * <p>The publisher should inject an ApplicationEventPublisher object.
 * <p>The listener should implement the ApplicationListener interface.
 */

class CustomSpringEvent extends ApplicationEvent {
    private static final long serialVersionUID = -8053143381029977953L;

    private String message;

    public CustomSpringEvent(final Object source, final String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}


class GenericSpringEvent<T> {

    private final T what;
    protected final boolean success;

    public GenericSpringEvent(final T what, final boolean success) {
        this.what = what;
        this.success = success;
    }

    public T getWhat() {
        return what;
    }

    public boolean isSuccess() {
        return success;
    }

}

class GenericStringSpringEvent extends GenericSpringEvent<String> {

    GenericStringSpringEvent(final String what, final boolean success) {
        super(what, success);
    }

}


class GenericSpringAppEvent<T> extends ApplicationEvent {

    private final T what;

    public GenericSpringAppEvent(final Object source, final T what) {
        super(source);
        this.what = what;
    }

    public T getWhat() {
        return what;
    }

}


class GenericStringSpringAppEvent extends GenericSpringAppEvent<String> {

    GenericStringSpringAppEvent(final Object source, final String what) {
        super(source, what);
    }

}
