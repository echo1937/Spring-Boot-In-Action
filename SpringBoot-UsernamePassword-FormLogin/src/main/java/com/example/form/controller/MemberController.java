package com.example.form.controller;

import com.example.form.model.Member;
import com.example.form.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    final MemberRepository memberRepository;

    @GetMapping({"/greet", "welcome"})
    public ResponseEntity<Member> greet(HttpServletRequest request) {
        Optional<Member> byUsername = memberRepository.findByUsername(request.getUserPrincipal().getName());
        return ResponseEntity.of(byUsername);
    }
}
