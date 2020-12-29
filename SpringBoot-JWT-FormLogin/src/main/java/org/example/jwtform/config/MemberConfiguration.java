package org.example.jwtform.config;


import lombok.RequiredArgsConstructor;
import org.example.jwtform.model.Member;
import org.example.jwtform.repository.MemberRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class MemberConfiguration {
    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            Set<String> role_user = new HashSet<>(Collections.singletonList("ROLE_USER"));
            Set<String> role_user_admin = new HashSet<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
            List<Member> members = Arrays.asList(
                    new Member("monika", passwordEncoder.encode("123456"), "Monika", role_user_admin, null),
                    new Member("jack", passwordEncoder.encode("123456"), "Jack", role_user, null),
                    new Member("peter", passwordEncoder.encode("123456"), "Peter", role_user, null));
            memberRepository.saveAll(members);
            System.out.println("H2数据库初始化完毕");
        };
    }
}
