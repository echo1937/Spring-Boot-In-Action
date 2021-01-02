package org.example.jwtform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.jwtform.auth.JwtTokenProvider;
import org.example.jwtform.model.Member;
import org.example.jwtform.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    final JwtTokenProvider jwtTokenProvider;
    final MemberRepository memberRepository;
    final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 实现UserDetailsService
    @Bean
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
            logger.info("H2数据库初始化完毕");
        };
    }

    // 自定义authenticationEntryPoint的注册
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

    // 自定义accessDeniedHandler的注册
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }

    // 自定义authenticationSuccessHandler的注册
    @Bean
    SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");

                Map<String, String> map = new HashMap<>();
                map.put("token", jwtTokenProvider.authenticationScheme + " " + jwtTokenProvider.generate(authentication));
                response.getWriter().println(new ObjectMapper().writeValueAsString(map));
            }
        };
    }

    // 自定义JWT Filter
    @Bean
    OncePerRequestFilter oncePerRequestFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                String bearerToken = request.getHeader(jwtTokenProvider.authenticationHeader);
                if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtTokenProvider.authenticationScheme)) {
                    String token = bearerToken.substring(jwtTokenProvider.authenticationScheme.length() + 1);
                    if (StringUtils.hasText(token) && jwtTokenProvider.validate(token)) {
                        SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.toAuthentication(token));
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 不设置http.formLogin(), 就没有UsernamePasswordAuthenticationFilter和DefaultLoginPageGeneratingFilter
        http.formLogin().successHandler(savedRequestAwareAuthenticationSuccessHandler());

        // https://www.cnblogs.com/summerday152/p/13635948.html
        // 插入JwtFilter, 在HttpSecurity:2536打断点可以看到Filter列表
        http.addFilterBefore(oncePerRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 会使得DefaultLoginPageGeneratingFilter失效, 进而看不到Spring Security自带的登录页面
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler());

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
