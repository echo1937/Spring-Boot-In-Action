# Getting Started

### 项目来源

* 教程 - [How To Do @Async in Spring](https://www.baeldung.com/spring-async)

### 扩展阅读

* [Spring线程池ThreadPoolTaskExecutor用法](https://juejin.cn/post/7033369137543905311)
* [我会手动创建线程，为什么让我使用线程池？](https://www.cnblogs.com/FraserYu/p/12749603.html)
* [说一下线程池里面的几把锁](https://zhuanlan.zhihu.com/p/432425262)
    * GitHub不错，任务调度流程图很形象

### 源码分析

* [Java线程池ThreadPoolExecutor原理源码分析](https://juejin.cn/post/7079605753094340644)
* [Spring @EnableAsync 注解原理](https://plentymore.github.io/2018/12/29/Spring-EnableAsync-%E6%B3%A8%E8%A7%A3%E5%8E%9F%E7%90%86/)

### 整合Junit5

* [JUnit5 @RunWith](https://www.baeldung.com/junit-5-runwith)
* [整合JUnit5时出现空指针异常](https://blog.csdn.net/weixin_45088605/article/details/124470799)

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
    - 同时定义两个Executor时, 可以参考SpringAsyncConfig的写法
    - APP级别的Executor在定义时, 需要显式调用initialize后返回, 否则会报错
5. 异常处理
    - 在发生异常时, Future泛型作为返回值的方法可以通过Future.get()获取异常内容,
    - void方法就需要实现AsyncConfigurer接口的getAsyncUncaughtExceptionHandler方法来处理此类异常
6. 测试相关
    - 由于JUnit4并没有完整启动SpringBoot, 所以需要@ComponentScan("com.example.asynchronous.service")
      来扫描bean，否则AsyncAnnotationExampleIntegrationTest无法找到bean
    - 通过XML进行异步配置不是重点, 过一眼就好

### 内容补充
1. 查看com.example.asynchronous.config.SpringAsyncConfig, 里面补充了很多关于线程池初始化的方法和技巧