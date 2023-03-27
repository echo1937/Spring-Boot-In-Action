# Mybatis-Plus

### 扩展

For further reference, please consider the following sections:

* [逻辑删除](https://baomidou.com/pages/6b03c5/)
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

