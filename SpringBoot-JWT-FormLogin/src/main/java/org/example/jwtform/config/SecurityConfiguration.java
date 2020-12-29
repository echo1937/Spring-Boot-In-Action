package org.example.jwtform.config;

import lombok.RequiredArgsConstructor;
import org.example.jwtform.auth.JwtAccessDeniedHandler;
import org.example.jwtform.auth.JwtAuthenticationEntryPoint;
import org.example.jwtform.auth.JwtFilter;
import org.example.jwtform.auth.FormLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    final JwtFilter jwtFilter;
    final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    final FormLoginSuccessHandler formLoginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 不设置http.formLogin(), 就没有UsernamePasswordAuthenticationFilter和DefaultLoginPageGeneratingFilter
        http.formLogin().successHandler(formLoginSuccessHandler);

        // https://www.cnblogs.com/summerday152/p/13635948.html
        // 插入JwtFilter, 在HttpSecurity:2536打断点可以看到Filter列表
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 会使得DefaultLoginPageGeneratingFilter失效, 进而看不到Spring Security自带的登录页面
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler);

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()   // 放通h2-console的登录页面
                .antMatchers("/greet").hasRole("ADMIN")
                .anyRequest().authenticated();

        // 放通h2-console登录后的页面
        // https://stackoverflow.com/questions/43794721/spring-boot-h2-console-throws-403-with-spring-security-1-5-2
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
