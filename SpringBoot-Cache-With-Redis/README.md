# Getting Started

### Jedis vs Lettuce和commons-pool2

1. 1.x默认Driver为Jedis，2.x默认Driver为Lettuce（需要引入commons-pool2作为连接池）,
    + [Spring Boot 2.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide#redis)
2. 如果2.x环境需要设置Jedis为默认Driver，可以参考
    + [Use Jedis Instead of Lettuce](https://qunfanyi.com/chapter/89267.html)
    + [Introduction to Spring Data Redis](https://www.baeldung.com/spring-data-redis-tutorial#2-custom-connection-properties)
      ，相关API已经废弃，不推荐

### SpringBoot 2.x 的配置方法

1. 通过spring-boot-starter-cache导入依赖；
2. spring-boot-autoconfigureCache的CacheAutoConfiguration负责全局cache管理，RedisCacheConfiguration负责redis cache的配置；
3. RedisCacheConfiguration内有@Bean public RedisCacheManager cacheManager()方法，通过6个入参来设置RedisCacheManager；
4. 一般我们通过两个入参来控制RedisCacheManager的构建：
    + ObjectProvider <org.springframework.data.redis.cache.RedisCacheConfiguration> redisCacheConfiguration
    + ObjectProvider \<RedisCacheManagerBuilderCustomizer\> redisCacheManagerBuilderCustomizers

### 相关链接：
+ [Spring Boot Cache with Redis](https://www.baeldung.com/spring-boot-redis-cache) ，
+ [spring-data-redis 2.0 的使用](https://segmentfault.com/a/1190000017953598) ，RedisConfig部分写得较为模糊，也不规范
+ [how-to-create-rediscachemanager-in-spring-data-2-0-x](https://stackoverflow.com/questions/51418161/how-to-create-rediscachemanager-in-spring-data-2-0-x)

### SpringBoot 1.x 的配置方法

1. 第一步相同；
2. 第二步相同；
3. RedisCacheConfiguration内有@Bean public RedisCacheManager cacheManager()，通过入参RedisTemplate<Object, Object>
   redisTemplate来控制
4. 我们在spring-boot-autoconfigureCache的RedisAutoConfiguration设置RedisTemplate
