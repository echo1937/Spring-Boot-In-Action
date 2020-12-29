# SpringBoot JWT Token Based 

## 场景
- H2数据库
- Spring Data JPA
- Spring Security
## 知识点
- JPA的@ElementCollection注解
- ApplicationRunner初始化数据库
- UserDetailsService.loadUserByUsername()
- 启用h2-console（如何放行登录方法和管理页面）
- 自定义Controller以实现JWT的login
## 细节
* ExceptionTranslationFilter负责捕获并处理AuthenticationException和AccessDeniedException
   * 处理AuthenticationException时, 执行authenticationEntryPoint.commence(request, response, reason), 所以JwtAccessDeniedHandler重载AuthenticationEntryPoint
   * 处理AccessDeniedException时, 执行accessDeniedHandler.handle(request, response, (AccessDeniedException) exception), 所以JwtAuthenticationEntryPoint重载AccessDeniedHandler
* 在SecurityFilterChain中插入JwtFilter, 它从HTTP Request的Header中取得Token, 校验后将JWT Token转成Authentication, 并设定到SecurityContextHolder
   * 后续的Filter可以检查Authentication确定用户
   * 后续的Filter可以检查Authentication确定权限
* 如果进来的请求是POST /login, 它会通过JwtFilter, 也会通过ExceptionTranslatorFilter和FilterSecurityInterceptor, 因为我们设定放行/login, 它最终会抵达MemberController
   * 通过HttpServletRequest生成UsernamePasswordAuthenticationToken
   * 执行.authenticate(authentication)方法进行用户名和密码的校验(通过比对UserDetailsService的实现类MemberUserDetailsService), 最终获得authentication
   * 将authentication设定到SecurityContextHolder中
   * 生成JWT Token, 设定到HTTP Request的Header中, 并通过HTTP Response返回
* JwtTokenProvider是JWT的工具类, 用以生成、校验和获取Token的信息
* SecurityConfiguration的细节
   * 使用http.addFilterBefore()注册Filter(UsernamePasswordAuthenticationFilter.class之前)
   * 使用http.sessionManagement().sessionCreationPolicy()关闭session, 改为无状态的JWT
   * http.exceptionHandling()注册我们的Bean, 控制AuthenticationException和AccessDeniedException捕获后的处理逻辑