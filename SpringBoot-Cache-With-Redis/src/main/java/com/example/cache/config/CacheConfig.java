package com.example.cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

@EnableCaching
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CacheConfig {
    final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * /usr/local/opt/redis@4.0/bin/redis-cli -h 20.26.39.43 -p 6379 --raw
     * select 5
     * keys *
     * flushall
     *
     * @return --raw可以使redis-cli正常显示中文
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return defaultCacheConfiguration();
    }

    /**
     * @return jdk序列化
     */
    private RedisCacheConfiguration defaultCacheConfiguration() {
        logger.info("使用的序列化方式为:JDK");
        // https://www.baeldung.com/spring-boot-redis-cache
        // https://stackoverflow.com/questions/51418161/how-to-create-rediscachemanager-in-spring-data-2-0-x
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(6))
                .disableCachingNullValues();
    }

    /**
     * @return Jackson序列化
     */
    private RedisCacheConfiguration jsonCacheConfiguration() {
        logger.info("使用的序列化方式为:GenericJackson2JsonRedisSerializer");
        ObjectMapper mapper = JsonMapper.builder()
                // 默认ObjectMapper序列化结果不带类型信息, 所以无法 反序列化 对象类型
                .activateDefaultTyping(new LaissezFaireSubTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING)
                // 添加时间对象的序列化方法 https://stackoverflow.com/questions/21384820/is-there-a-jackson-datatype-module-for-jdk8-java-time
                .findAndAddModules()
                .build();

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(6))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(mapper)));
    }


    @Bean
    public KeyGenerator globalKeyGenerator() {
        return (target, method, params) -> LocalDate.now().toString() + "::" + method.getName() + Arrays.asList(params).toString();
    }
}
