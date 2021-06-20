# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.12.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.12.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#boot-features-redis)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

# 一、SpringBoot 2.x的配置方法；
### 1、通过spring-boot-starter-cache导入依赖；
### 2、spring-boot-autoconfigureCache的CacheAutoConfiguration负责全局的cache管理，RedisCacheConfiguration负责redis cache的配置；
### 3、RedisCacheConfiguration内有@Bean public RedisCacheManager cacheManager()方法，通过6个入参来设置RedisCacheManager；
### 4、一般我们通过两个入参来控制RedisCacheManager的构建：
+ ObjectProvider<org.springframework.data.redis.cache.RedisCacheConfiguration> redisCacheConfiguration
+ ObjectProvider\<RedisCacheManagerBuilderCustomizer\> redisCacheManagerBuilderCustomizers

### 5、相关链接：
+ [Spring Boot Cache with Redis](https://www.baeldung.com/spring-boot-redis-cache) ，
+ [spring-data-redis 2.0 的使用](https://segmentfault.com/a/1190000017953598) ，RedisConfig部分写得较为模糊，也不规范
+ [how-to-create-rediscachemanager-in-spring-data-2-0-x](https://stackoverflow.com/questions/51418161/how-to-create-rediscachemanager-in-spring-data-2-0-x)


# 二、SpringBoot 1.x的配置方法；
### 1、第一步相同；
### 2、第二步相同；
### 3、RedisCacheConfiguration内有@Bean public RedisCacheManager cacheManager()，通过入参RedisTemplate<Object, Object> redisTemplate来控制
### 4、我们在spring-boot-autoconfigureCache的RedisAutoConfiguration设置RedisTemplate

# 三、Jedis vs Lettuce和commons-pool2
### 1、1.x默认Driver为Jedis，2.x默认Driver为Lettuce（需要引入commons-pool2作为连接池）,
+ [Spring Boot 2.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide#redis)
### 2、如果2.x环境需要设置Jedis为默认Driver，可以参考 
+ [Use Jedis Instead of Lettuce](https://qunfanyi.com/chapter/89267.html)
+ [Introduction to Spring Data Redis](https://www.baeldung.com/spring-data-redis-tutorial#2-custom-connection-properties) ，相关API已经废弃，不推荐