# SpringBoot MyBatis Single Druid DataSource

## 场景
- MySQL数据库
- mybatis-spring-boot-starter
- druid-spring-boot-starter
## 知识点
1. entity层
    * 别名：model层 ，domain层
    * 用途：实体层，用于存放我们的实体类，与数据库中的属性值基本保持一致，实现set和get的方法。
    * 例子：user表的实体User
1. mapper层
    * 别名：dao层
    * 用途：对数据库进行数据持久化操作，它的方法语句是直接针对数据库操作的，主要实现一些增删改查操作，在MyBatis中方法主要与与xxx.xml内相互一一映射。
    * 示例：UserMapper
1. service层
    * 别名：无
    * 用途：给controller层的类提供接口进行调用。一般就是自己写的方法封装起来，就是声明一下，具体实现在serviceImpl中。
    * 示例：UserService
1. controller层
    * 别名：web层
    * 用途：负责具体模块的业务流程控制，需要调用service逻辑设计层的接口来控制业务流程。因为service中的方法是我们使用到的，controller通过接收前端H5或者App传过来的参数进行业务操作，再将处理结果返回到前端。 
    * 示例：UserController
## 细节
* dao层
    * 一般采用"接口+注解"或者"接口+xml"的方式
        * "接口+注解": 可以实现去XML化，Java Text Blocks普及后会有更大发挥空间
        * "接口+xml": 打包后, 接口文件和xml必需在同一个位置下
    * @Mapper用在接口类上，在编译之后会生成相应的接口实现类
    * @MapperScan扫描指定包下所有的接口类，所有接口在编译之后都会生成相应的实现类，可以添加在SpringBootApplication启动类上
    * [@Repository和@Mapper注解的区别](https://juejin.cn/post/6844903958985736205)
* service层
    * 现在一般不会拆分Service和ServiceImpl
* 完整的MyBatis设置
    * application.yaml
        * 配置数据源
        * 配置下划线转驼峰("接口+注解"时需要)
    * SpringBootApplication启动类
        * 配置@MapperScan, 扫描接口类
    * 文件
        * entity + mapper + service
* xml文件的定位
  * xml文件和mapper文件如果不在同一路径下，需要使用[mybatis.mapper-locations配置xml文件路径](https://blog.csdn.net/zxl646801924/article/details/88669809)
* 如何打印SQL
  * 如果是单数据源, 只需设置mybatis.configuration.log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  * 如果是多数据源, 如[SpringBoot-MyBatis-Multi-Druid-DataSource](..%2FSpringBoot-MyBatis-Multi-Druid-DataSource)和[SpringBoot-MyBatis-Multi-Hikari-DataSource](..%2FSpringBoot-MyBatis-Multi-Hikari-DataSource), 需要参考[Mybatis打印SQL](https://blog.csdn.net/weixin_36507118/article/details/85342398)

## 更新
* 版本（2020-12-30）
  * 升级到JDK 17
  * 升级到springboot 2.5.8
  * druid-spring-boot-starter升级到1.2.8（IDEA提示推荐）
  * mybatis-spring-boot-starter升级到2.2.0（IDEA提示推荐, 兼容springboot 2.5+）

## 扩展阅读

* [mybatis-spring](https://mybatis.org/spring/index.html)
* [mybatis-spring-boot-autoconfigure](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Spring Boot(六)：如何优雅的使用 Mybatis](https://www.cnblogs.com/ityouknow/p/6037431.html)
* [Google: mybatis-spring-boot-starter](https://www.google.com/search?q=mybatis-spring-boot-starter&oq=mybatis-spring-boot-starter)
