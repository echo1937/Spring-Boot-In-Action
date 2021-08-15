package com.example.validation.validation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ListGroupValidationException extends ValidationException {
    private Map<Integer, Set<ConstraintViolation<Object>>> errors;
}
