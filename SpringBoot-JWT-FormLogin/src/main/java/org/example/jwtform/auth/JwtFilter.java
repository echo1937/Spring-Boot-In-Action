package org.example.jwtform.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    private String extractToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(jwtTokenProvider.authenticationHeader);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtTokenProvider.authenticationScheme)) {
            return bearerToken.substring(jwtTokenProvider.authenticationScheme.length() + 1);
        }
        return "";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = this.extractToken(((HttpServletRequest) request));
        if (StringUtils.hasText(token) && jwtTokenProvider.validate(token)) {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.toAuthentication(token));
        }
        chain.doFilter(request, response);
    }
}
