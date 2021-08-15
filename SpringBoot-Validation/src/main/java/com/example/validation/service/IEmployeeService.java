package com.example.validation.service;

import com.example.validation.entity.Employee;
import com.example.validation.validation.annotation.ListGroup;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

/* @Validated注解在接口上表明它的实现类都进行验证;
   如果只有部分实现类需要进行验证, 可以把注解写在实现类上 */
@Validated
public interface IEmployeeService {

    // 级联验证, 分组验证
    @Validated(value = {Employee.Add.class, Default.class})
    void add(@Valid Employee employee);

    // 分组验证, List中的分组验证
    void addList(@ListGroup(groupings = {Employee.Add.class, Default.class}) List<Employee> employeeList);

    // 入参验证、返回值验证
    @NotNull Employee getById(@Max(2) Integer id);

    // https://stackoverflow.com/questions/30929255/how-to-avoid-hibernate-validator-constraintdeclarationexception
}
