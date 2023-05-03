# SpringBoot JPA Multiple DataSource

## 项目来源
* [Spring JPA – Multiple Databases](https://www.baeldung.com/spring-data-jpa-multiple-databases)

## 场景

- MySQL数据库
- HikariCP数据库连接池
- spring-boot-starter-data-jpa
- spring-boot-configuration-processor

## 知识点
- @EnableTransactionManagement [(Transactions with Spring and JPA)](https://www.baeldung.com/transaction-configuration-with-jpa-and-spring)
    - Spring 3.1 introduces the @EnableTransactionManagement annotation that we can use in a @Configuration class and enable transactional support:
      ```java
      @Configuration
      @EnableTransactionManagement
      public class PersistenceJPAConfig{

          @Bean
          public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
            //...
          }
            
          @Bean
          public PlatformTransactionManager transactionManager(){
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
            return transactionManager;
          }
      }
      ```
      However, if we're using a Spring Boot project, and have a spring-data-* or spring-tx dependencies on the classpath, then transaction management will be enabled by default.

## 细节

- [Difference between junit-vintage-engine and junit-jupiter-engine?](https://stackoverflow.com/questions/59193282/difference-between-junit-vintage-engine-and-junit-jupiter-engine)
- [JUnit tests always rollback the transactions](https://stackoverflow.com/a/27296926/2411714)
- [How to set hibernate.format_sql in spring-boot?](https://stackoverflow.com/a/42457442/2411714)

## 扩展阅读

* [https://github.com/Java-Techie-jt/spring-boot-multiple-datasource](https://github.com/Java-Techie-jt/spring-boot-multiple-datasource)
* [https://github.com/jahe/spring-boot-multiple-datasources](https://github.com/jahe/spring-boot-multiple-datasources)