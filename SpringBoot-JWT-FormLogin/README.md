# SpringBoot JWT Form Login

## 场景
- H2数据库
- Spring Data JPA
- Spring Security
## 知识点
- JPA的@ElementCollection注解
- ApplicationRunner初始化数据库
- UserDetailsService.loadUserByUsername()
- 启用h2-console（如何放行登录方法和管理页面）
- 使用form login以实现JWT的login
## 细节
* 设定http.formLogin()后, FilterChain中会添加UsernamePasswordAuthenticationFilter和DefaultLoginPageGeneratingFilter
  * UsernamePasswordAuthenticationFilter会进行form login, 请求为POST方法, body为x-www-form-urlencoded
  * DefaultLoginPageGeneratingFilter会提供自带的登录页面
  * FormLoginSuccessHandler用于表单验证通过后, 生成和返回JWT Token, 无需再自定义/login的Controller
  * 我们也可以自定义AuthenticationFailureHandler的实现, 这样就不需要JwtAuthenticationEntryPoint嗯
* 设定http.exceptionHandling()后, FilterChain会剔除DefaultLoginPageGeneratingFilter
  * http.formLogin()和http.exceptionHandling()共存时, 只添加UsernamePasswordAuthenticationFilter
* 其他细节
  * JwtAccessDeniedHandler用于AccessDeniedException异常的处理
  * JwtAuthenticationEntryPoint用于AuthenticationException异常的处理
## 测试方法：
* 权限
  * monika：ROLE_USER、ROLE_ADMIN
  * jack and peter：ROLE_USER
  * /greet: 需求权限ROLE_ADMIN
* 登录
```
POST http://localhost:8080/login
Content-Type: application/x-www-form-urlencoded

username=jack&password=123456
```
* 鉴权访问
```
GET http://localhost:8080/greet
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb25pa2EiLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4sUk9MRV9VU0VSIiwiZXhwIjoxNjA5NTc3Njc1fQ.Ct2961oQUgN2h-3Lt0BOMr9E-a8KxOb1ggVKmRn-FE0sIi0hSg3johDzJxrp3tf7zESvQwEIy8-igb-JZkbsIA
```