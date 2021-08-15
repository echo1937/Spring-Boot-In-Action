package com.example.validation.validation.validator;


import com.example.validation.validation.annotation.ListGroup;
import com.example.validation.validation.exception.ListGroupValidationException;
import com.example.validation.validation.util.ValidatorUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;


@SuppressWarnings("rawtypes")
public class ListGroupValidator implements ConstraintValidator<ListGroup, List> {

    private Class<?>[] groupings;
    private boolean quickFail;

    @Override
    public void initialize(ListGroup listGroup) {
        groupings = listGroup.groupings();
        quickFail = listGroup.quickFail();
    }

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        Map<Integer, Set<ConstraintViolation<Object>>> map = new HashMap<>();
        IntStream.range(0, value.size()).forEach(index -> {
            // 取得List的元素
            Object object = value.get(index);
            // 校验元素
            Set<ConstraintViolation<Object>> error = ValidatorUtils.validator.validate(object, groupings);
            if (error.size() > 0) {
                // 如果校验错误不为空, 则进行保存至map
                map.put(index, error);
                // 如果快速失败生效, 则发生错误后立刻抛出异常
                if (quickFail) throw new ListGroupValidationException(map);
            }
        });

        // 如果map不为空, 则抛出异常
        if (map.size() > 0) throw new ListGroupValidationException(map);

        return true;
    }
}
