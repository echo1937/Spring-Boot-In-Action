package org.example.jwtform.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwtform.model.Member;
import org.example.jwtform.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/greet")
    public ResponseEntity<Member> greet(HttpServletRequest request) {
        Optional<Member> byUsername = memberRepository.findByUsername(request.getUserPrincipal().getName());
        assert byUsername.orElse(null) != null;
        return ResponseEntity.ok(byUsername.orElse(null));
    }

}
