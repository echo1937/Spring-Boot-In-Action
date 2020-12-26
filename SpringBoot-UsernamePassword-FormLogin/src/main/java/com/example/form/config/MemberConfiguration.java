package com.example.form.config;

import com.example.form.model.Member;
import com.example.form.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
            memberRepository.saveAll(
                    Arrays.asList(
                            new Member("monika", passwordEncoder.encode("123456"), "Monika", role_user_admin, null),
                            new Member("jack", passwordEncoder.encode("123456"), "Jack", role_user, null),
                            new Member("peter", "123456", "Peter", role_user, null)
                    )
            );
            System.out.println("测试");
            System.out.println("ApplicationRunner with args:" + Arrays.toString(args.getSourceArgs()));
        };
    }
}
