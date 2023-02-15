# Getting Started


### 来源

* [How To Do @Async in Spring](https://www.baeldung.com/spring-async)

### 内容详解

1. 总览
   - Spring的异步支持
   - Spring的事件支持
2. 开启异步支持
   - 启用@EnableAsync, 额外提到了annotation, mode, proxyTargetClass, order等选项
   - XML配置
3. @Async注解
   - 限制: 
     - @Async方法所在类必须是public的, 不可以自调用(主要是和类的Proxy有关)
     - @Async方法所在类必须是Spring容器管理的bean对象
     - [什么情况会使@Async失效](https://www.cnblogs.com/tsangyi/p/13303018.html)
   - 方法的返回类型可以是void, 也可以是Future泛型, 或者AsyncResult泛型来返回结果
4. Executor
   - 可以定义一个方法级的Executor, 以@Async("threadPoolTaskExecutor")的方式指定该方法的Executor
   - 可以定义一个APP级别的Executor, 该Executor是全局默认的Executor()
   - 同时定义两个Executor时, 可以把@EnableAsync移动到启动类上面(两边都标注也是可以的)
   - APP级别的Executor在定义时, 需要显式调用initialize后返回, 否则会报错
5. 异常处理
   - 在发生异常时, Future泛型作为返回值的方法可以通过Future.get()获取异常内容, 
   - void方法就需要实现AsyncConfigurer接口的getAsyncUncaughtExceptionHandler方法来处理此类异常

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.8/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.8/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
