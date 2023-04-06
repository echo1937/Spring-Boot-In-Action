# SpringBoot-Validation

## 1、级联验证
- 一对多级联
    ```http request
    ###
    POST http://localhost:8080/department
    content-type: application/json
    
    {
      "id": 12,
      "parentId": 0,
      "name": "技术部",
      "createTime": "2020-12-03T00:00:00",
      "employeeList": [
        {}
      ]
    }
    ```
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "employeeList[0].age": "must not be null",
        "employeeList[0].name": "must not be empty",
        "id": "主键不可以有值",
        "employeeList[0].title": "must not be empty"
      }
    }
    ```

- 一对一级联
    ```http request
    ###
    POST http://localhost:8080/employee
    content-type: application/json
    
    {
      "age": 21,
      "title": "中级",
      "department": {
        "id": 0,
        "parentId": 0,
        "name": "技术部",
        "createTime": "2020-12-03T00:00:00"
      }
    }
    ```
    
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "name": "must not be empty",
        "department.id": "主键不可以有值"
      }
    }
    ```

## 2、分组验证
- PUT方法 vs POST方法
    ```http request
    ###
    PUT http://localhost:8080/employee
    content-type: application/json
    
    {
      "age": 21,
      "title": "中级",
      "department": {
        "id": 0,
        "parentId": 0,
        "name": "技术部",
        "createTime": "2020-12-03T00:00:00"
      }
    }
    ```
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "name": "must not be empty",
        "department.id": "主键不可以有值",
        "id": "must not be null"
      }
    }
    ```

## 3、Bean参数间的逻辑校验
  - 实现: 
    - Employee上增加@GroupSequenceProvider(EmployeeGroupSequenceProvider.class);
    - EmployeeGroupSequenceProvider实现DefaultGroupSequenceProvider的接口方法, 完成逻辑处理;
    - 通过@Pattern等注解完成校验
  - 备注: 所有(非级联)校验通过, 才会进入参数间的逻辑校验的环节;
  - 拓展: https://blog.csdn.net/f641385712/article/details/99725482
    ```http request
    ###
    POST http://localhost:8080/employee
    content-type: application/json
    
    {
      "name": "zs",
      "age": 21,
      "title": "中级",
      "department": {
        "id": 0,
        "parentId": 0,
        "name": "技术部",
        "createTime": "2020-12-03T00:00:00"
      }
    }
    ```
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "department.id": "主键不可以有值",
        "title": "必须以初级开头"
      }
    }
    ```
## 4、自定义注解
  - 普通类型: @MultipleOfThree (是否能被3整除)
    ```http request
    ###
    POST http://localhost:8080/job
    content-type: application/json
    
    {
      "labels": [
        "",
        "活泼"
      ]
    }
    ```
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "labels[0]": "size must be between 1 and 2147483647",
        "labels": "必须是 3 的倍数"
      }
    }
    ```    
  - 高级类型: @ListGroup (List中的分组校验)
  
## 5、在Service中做参数验证
- 在Controller中做参数校验抛出MethodArgumentNotValidException异常, 在Service中做参数校验抛出ConstraintViolationException异常
- 场景1, 无Interface: 
  - 和Controller中验证写法无异;
- 场景2, 有Interface: 
  - @Validated可以标记在实现类上, 也可以标记在接口上, 后一种情况的所有实现类都会进行参数校验;
  - 接口中, 方法入参、返回值上的注解不能省略, 否则会抛出ConstraintDeclarationException异常
  - 实现类中, 方法入参、返回值上注解可以省略也可以百分百保留, 但不能进行修改, 比如@Min(9)变成@Min(8)
- 级联验证、分组验证、Bean参数间的逻辑校验 功能依然可用
  - 级联验证 + 分组验证 + Bean参数间的逻辑校验
    ```http request
    ###
    POST http://localhost:8080/service/employee
    content-type: application/json
    
    {
      "age": 16,
      "title": "中级",
      "department": {
        "id": 0,
        "parentId": 0,
        "name": "技术部",
        "createTime": "2020-12-03T00:00:00"
      }
    }
    ```
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "com.example.validation.service.EmployeeService add.employee.department.id": "主键不可以有值",
        "com.example.validation.service.EmployeeService add.employee.title": "必须以初级开头",
        "com.example.validation.service.EmployeeService add.employee.id": "must be null"
      }
    }
    ```
  - 级联验证 + @ListGroup (List中的分组校验)
    ```http request
    ###
    POST http://localhost:8080/service/employeeList
    content-type: application/json
    
    [
      {
        "id": 0,
        "age": 21,
        "title": "中级",
        "name": "zs",
        "department": {
          "id": 0,
          "parentId": 0,
          "name": "技术部",
          "createTime": "2020-12-03T00:00:00"
        }
      },
      {}
    ]
    ```
    ```json
    {
      "success": false,
      "code": "1000",
      "msg": "参数不正确",
      "data": {
        "0": {
          "title": "必须以初级开头",
          "id": "must be null",
          "department.id": "主键不可以有值"
        },
        "1": {
          "title": "must not be empty",
          "name": "must not be empty",
          "age": "must not be null"
        }
      }
    }
    ```
## 6、补充：
- [Validation in Spring Boot](https://www.baeldung.com/spring-boot-bean-validation)
- [@Validated和@Valid区别](https://blog.csdn.net/qq_27680317/article/details/79970590)
- [@Valid和@Validated 的区别和使用](https://my.oschina.net/u/4404311/blog/3230780)
- [Spring Web + Validation](https://segmentfault.com/a/1190000023959475)
- [@GroupSequenceProvider和@GroupSequence](https://blog.csdn.net/f641385712/article/details/99725482)
- [Hibernate Validator 7.0.1.Final](https://docs.jboss.org/hibernate/validator/7.0/reference/en-US/html_single/#_code_groupsequenceprovider_code)