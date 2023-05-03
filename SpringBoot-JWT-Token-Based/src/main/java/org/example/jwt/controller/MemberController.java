package org.example.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt.auth.JwtTokenProvider;
import org.example.jwt.model.Member;
import org.example.jwt.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/greet")
    public ResponseEntity<Member> greet(HttpServletRequest request) {
        Optional<Member> byUsername = memberRepository.findByUsername(request.getUserPrincipal().getName());
        assert byUsername.orElse(null) != null;
        return ResponseEntity.ok(byUsername.orElse(null));

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request,
                                                     HttpServletResponse httpServletResponse) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"));
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generate(authentication);
        httpServletResponse.setHeader(jwtTokenProvider.authenticationHeader, jwtTokenProvider.authenticationScheme + " " + token);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

}
