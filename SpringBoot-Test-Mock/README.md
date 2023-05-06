# Getting Started

### 背景阅读

- [在SpringBoot中编写Mock单元测试](https://www.jianshu.com/p/a6b616403cd0)
- [when(...).thenReturn(...)和doReturn(...).when(...)的区别](https://www.cnblogs.com/lanqi/p/7865163.html)

### @Spy和@Mock

- @Mock：对函数的调用均执行mock（即虚假函数），不执行真正部分。
  - when(...).thenReturn(...)和doReturn(...).when(...)没有区别, stub时都不会执行真实方法
- @Spy：对函数的调用均执行真正部分。
  - when(...).thenReturn(...)和doReturn(...).when(...)有区别, 后者stub时不会执行真实方法
- 请查考ExampleServiceTest示例体会区别和用法
  

### @InjectMocks

- 在单元测试中，我们通常需要测试一个类或方法的行为，但是这个类或方法可能会依赖其他组件或对象，例如数据访问对象、服务等。
  - @InjectMocks会将带有@Spy和@Mock注解的对象尝试注入到被测试的目标类中。
- 请参考InjectMockExampleServiceTest示例体会其用法


### Todo

- 如何写好Mock测试
  - 掌握基础的Junit知识, 分清Junit4和Junit5
- @MockBean,@SpyBean vs @Mock, @Spy
  - 区别、用法

### 参考来源

- [Mockito Tutorial](https://www.baeldung.com/mockito-series)
- [SpringBoot - 單元測試工具 Mockito](https://kucw.github.io/blog/2020/2/spring-unit-test-mockito/)

