# Getting Started



### 资料来源
- 教程 - [Spring Events](https://www.baeldung.com/spring-events)
  - By default Spring events are synchronous, the doStuffAndPublishAnEvent() method blocks until all listeners finish processing the event.
  - There's an alternative way of publishing events. If we return a non-null value from a method annotated with @EventListener as the result, Spring Framework will send that result as a new event for us. Moreover, we can publish multiple new events by returning them in a collection as the result of event processing.
- 扩展阅读 - [聊透Spring事件机制](https://juejin.cn/post/7140849555607650335)
  - 可以通过@Autowired和ApplicationEventPublisherAware获取publisher(ApplicationEventPublisher)
  - 关键类AbstractApplicationContext, AbstractApplicationEventMulticaster
  - ApplicationEventMulticaster设置线程池是全局的, 如果我们项目中有些事件需要异步处理, 又有些事件需要同步执行的, 可以借助@Async
  - Spring事件的处理，默认是同步依次执行。那如果前面的监听器出现了异常，并且没有处理异常，会对后续的监听器还能顺利接收该事件吗？答案不能的，因为异常中断了事件的发送。我们可以通过setErrorHandler()对异常进行统一的管理，不用繁琐的try catch了，

