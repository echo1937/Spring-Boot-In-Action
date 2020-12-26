package com.example.form.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 放通h2-console的登录页面
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/greet").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin().defaultSuccessUrl("/welcome");

        // 放通h2-console登录后的页面
        // https://stackoverflow.com/questions/43794721/spring-boot-h2-console-throws-403-with-spring-security-1-5-2
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
