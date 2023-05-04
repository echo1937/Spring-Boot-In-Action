# Mybatis-Plus

### 扩展

For further reference, please consider the following sections:

* [逻辑删除](https://baomidou.com/pages/6b03c5/)
  * 这个规范非常有必要进行统一, 防止外部调用时遗漏逻辑删除字段
* [通用枚举](https://baomidou.com/pages/8390a4/)
* [字段类型处理器(以JSON字段类型为例)](https://baomidou.com/pages/fd41d8/)
* [自动填充功能](https://baomidou.com/pages/4c6bcf/)
  * 填充create_time和update_time, 前者MySQL和PostgreSQL可以设置default now(), 后者MySQL支持default now() on update, PostgreSQL需要触发器实现
* [SQL注入器](https://baomidou.com/pages/42ea4a/)
  * todo 
* [多数据源](https://baomidou.com/pages/a61e1b/)
  * 这玩意文档付费, 不推荐使用, 本sample只是解决了MySQL和PostgreSQL交差演示时的切换问题
* [@OrderBy](https://baomidou.com/pages/223848/#interceptorignore)
  * 默认排序功能, PostgreSQL在更新某条记录后, select *返回时候该行会变成最后一行, 可以用此功能规避显式排序;

### 插件

The following guides illustrate how to use some features concretely:

* [插件主体](https://baomidou.com/pages/2976a3/)
* [分页插件](https://baomidou.com/pages/97710a/)
* [todo-乐观锁](https://baomidou.com/pages/0d93c0/)

### 外部链接

* [MySQL Connector/J 8.0 Connection URL Syntax](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html)
* [Initializing PostgreSQL JDBC Driver](https://jdbc.postgresql.org/documentation/use/)
* [PostgreSQL 20.11. Client Connection Defaults](https://www.postgresql.org/docs/15/runtime-config-client.html)
* [Mybatis-Plus处理MySQL的json类型](https://blog.csdn.net/qq_35098526/article/details/117912886)

### 遗留问题

```shell
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@230f1e70] was not registered for synchronization because synchronization is not active
JDBC Connection [HikariProxyConnection@755985113 wrapping org.postgresql.jdbc.PgConnection@63549d6b] will not be managed by Spring
==>  Preparing: SELECT id,nickname,sex,hobbies,ipaddress,create_time,update_time,deleted FROM user_table WHERE deleted=0
==> Parameters: 
<==    Columns: id, nickname, sex, hobbies, ipaddress, create_time, update_time, deleted
<==        Row: 1, 大漂亮, 1, ["游泳","健身"], 127.0.0.1, 2023-03-26 13:58:41.672, 2023-03-26 13:58:41.672, 0
<==        Row: 4, 大漂亮, 1, ["游泳","健身"], 127.0.0.1, 2023-03-26 13:58:49.366, 2023-03-26 13:58:49.366, 0
<==        Row: 5, 大漂亮, 1, ["游泳","健身"], 8.8.8.8, 2023-03-26 16:07:51.297, 2023-03-26 16:07:51.297, 0
<==        Row: 6, 大漂亮, 1, ["游泳","健身"], 8.8.8.8, 2023-03-26 17:33:35.079, 2023-03-26 17:33:35.079, 0
<==        Row: 7, 大漂亮, 1, ["游泳","健身"], 8.8.8.8, 2023-03-26 17:34:17.007, 2023-03-26 17:34:17.007, 0
<==        Row: 3, 傻子, 1, ["游泳","健身"], 127.0.0.1, 2023-03-26 13:58:48.062, 2023-03-27 10:06:49.642, 0
<==      Total: 6
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@230f1e70]
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6e5cd30a] was not registered for synchronization because synchronization is not active
2023-05-04 15:27:07.727  INFO 10309 --- [nio-8066-exec-2] c.e.s.config.MyMetaObjectHandler         : start insert fill ....
JDBC Connection [HikariProxyConnection@1961376866 wrapping org.postgresql.jdbc.PgConnection@63549d6b] will not be managed by Spring
==>  Preparing: INSERT INTO user_table ( nickname, sex, hobbies, ipaddress, create_time, update_time ) VALUES ( ?, ?, ?, ?, ?, ? )
==> Parameters: 大漂亮(String), 1(Integer), ["游泳","健身"](String), 8.8.8.8(String), 2023-05-04T15:27:07.730350(LocalDateTime), 2023-05-04T15:27:07.730580(LocalDateTime)
<==    Updates: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@6e5cd30a]
```

