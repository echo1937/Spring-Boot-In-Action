# SpringBoot MyBatis Multi Druid DataSource

## 场景

- MySQL数据库
- mybatis-spring-boot-starter
- druid-spring-boot-starter
- spring-boot-configuration-processor

## 知识点

- @MapperScan
- @Primary
- @ConfigurationProperties

## 细节

* 多数据源配置
    1. 配置主辅两个DataSourceConfig文件, 分别配置DataSource、SqlSessionFactoryBean、SqlSessionTemplate,
        1. 主配置文件的@Bean必须标明@Primary
        1. 利用@ConfigurationProperties("spring.datasource.druid")注解配置主数据源, 此注解可省略
        1. 利用@ConfigurationProperties("spring.datasource.second-druid")注解配置次数据源, 次注解不可省略
    1. 利用@MapperScan(basePackages = {"org.example.druid.multi.one.mapper"}, sqlSessionFactoryRef = "
       primarySqlSessionFactory")扫描负责的mapper
* 增强application.yaml
    * 问题:
        * yaml中, 主数据源druid的配置显示正常, 次数据源second-druid配置闪黄, 智能补全失效
    * 方案:
        * 新建SecondDruidDataSourceWrapper, 标注@ConfigurationProperties("spring.datasource.second-druid")
        * 利用return new SecondDruidDataSourceWrapper()构造DataSource
        * 搭配spring-boot-configuration-processor, 可以自动生成META-INF/spring-configuration-metadata.json, 解决配置闪黄智能补全失效的问题
* 备注
    * DruidDataSourceWrapper的访问作用域为default(无法extend), 只能重写

## 测试方法

* http://localhost:8080/address/selectOne?id=1
* http://localhost:8080/person/selectOne?id=1

## 扩展阅读

* [Spring Boot(七)：Mybatis 多数据源最简解决方案](https://www.cnblogs.com/ityouknow/p/6102399.html)