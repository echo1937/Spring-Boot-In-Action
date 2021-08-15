package com.example.validation.validation.annotation;


import com.example.validation.validation.validator.ListGroupValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * 支持 list 中的分组校验
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ListGroupValidator.class})
public @interface ListGroup {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    /**
     * 要验证的分组
     */
    Class<?>[] groupings() default {Default.class};

    boolean quickFail() default false;
}
