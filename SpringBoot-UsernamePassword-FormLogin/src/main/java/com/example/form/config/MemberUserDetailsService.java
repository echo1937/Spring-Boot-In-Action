package com.example.form.config;

import com.example.form.model.Member;
import com.example.form.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {
    final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Member> byUsername = memberRepository.findByUsername(s);

        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException(s + " was not found");
        }

        Member member = byUsername.get();
        List<SimpleGrantedAuthority> collect = member.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return User.withUsername(member.getUsername())
                .password(member.getPassword())
                .authorities(collect)
                .build();
    }
}
