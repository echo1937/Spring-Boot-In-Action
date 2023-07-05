# Configuration Metadata

## class方式

* @ConfigurationProperties + @Configuration 注解
    * 支持默认值
    * 支持JavaDoc作为YAML配置时的提示
    * 支持属性嵌套
    * 支持枚举和集合属性(还没掌握)
* 需要注意的问题
    * 与Lombook和AspectJ混用时有一些细节需要注意
* 相关链接
    * [Generating Your Own Metadata by Using the Annotation Processor](https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html#appendix.configuration-metadata.annotation-processor)
    * [A Guide to Spring Boot Configuration Metadata](https://www.baeldung.com/spring-boot-configuration-metadata)

## record方式

* @ConfigurationProperties + @EnableConfigurationProperties 注解
    * @Configuration不支持使用在record上, 所以需要换这种方式
    * 支持Java class作为record的入参, 也能把yaml的配置参数注入到record中, 但是yaml是爆黄的
* 相关链接
    * [使用record进行属性配置](https://www.bilibili.com/video/BV1FL4y1E7RV/?share_source=copy_web&vd_source=b280237d6a1e13b36bcbc268e67533c2&t=845) 