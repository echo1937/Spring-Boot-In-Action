# SpringBoot UsernamePassword FormLogin

## 场景
- H2数据库
- Spring Data JPA
- Spring Security
## 知识点
- JPA的@ElementCollection注解
- ApplicationRunner初始化数据库
- UserDetailsService.loadUserByUsername()
- 启用h2-console（如何放行登录方法和管理页面）
## 测试方法
- 用jack、peter访问/login(登录成功则跳转至welcome)，再访问/greet(权限不足)
- 用monika访问/login(登录成功则跳转至welcome)，再访问/greet(权限符合)

