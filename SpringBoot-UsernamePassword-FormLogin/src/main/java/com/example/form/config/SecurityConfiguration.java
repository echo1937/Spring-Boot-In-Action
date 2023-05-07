package com.example.form.config;

import com.example.form.model.Member;
import com.example.form.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    final MemberRepository memberRepository;

    // 实现PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 实现UserDetailsService
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Member> byUsername = memberRepository.findByUsername(username);

            if (byUsername.isEmpty()) {
                throw new UsernameNotFoundException(username + " was not found");
            }

            Member member = byUsername.get();
            List<SimpleGrantedAuthority> collect = member.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return User.withUsername(member.getUsername())
                    .password(member.getPassword())
                    .authorities(collect)
                    .build();
        };
    }

    // 初始化H2数据库
    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            Set<String> role_user = new HashSet<>(Collections.singletonList("ROLE_USER"));
            Set<String> role_user_admin = new HashSet<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
            List<Member> members = Arrays.asList(
                    new Member("monika", passwordEncoder().encode("123456"), "Monika", role_user_admin, null),
                    new Member("jack", passwordEncoder().encode("123456"), "Jack", role_user, null),
                    new Member("peter", passwordEncoder().encode("123456"), "Peter", role_user, null));
            memberRepository.saveAll(members);
            log.info("H2数据库初始化完毕");
        };
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
