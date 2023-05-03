package org.example.jwtform.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtTokenProvider {
    public final String authenticationHeader = "Authorization";
    public final String authenticationScheme = "Bearer";

    // 由openssl生成：openssl rand -base64 172
    private final String secret = "qsbWaaBHBN/I7FYOrev4yQFJm60sgZkWIEDlGtsRl7El/k+DbUmg8nmWiVvEfhZ91Y67Sc6Ifobi05b/XDwBy4kXUcKTitNqocy7rQ9Z3kMipYjbL3WZUJU2luigIRxhTVNw8FXdT5q56VfY0LcQv3mEp6iFm1JG43WyvGFV3hCkhLPBJV0TWnEi69CfqbUMAIjmymhGjcbqEK8Wt10bbfxkM5uar3tpyqzp3Q==";
    private final String claimAuthorities = "authorities";
    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


    public String generate(Authentication authentication) {
        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        final Date created = new Date();
        final Date expiration = new Date(created.getTime() + (60 * 60 * 1000));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(claimAuthorities, String.join(",", authorities))
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiration)
                .compact();
    }

    public Authentication toAuthentication(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        String role = (String) claims.get(claimAuthorities);
        List<SimpleGrantedAuthority> authorities = Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        User user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public boolean validate(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        try {
//            jwtParser.parse(token);
            jwtParser.parseClaimsJws(token).getBody();
            return true;
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return false;
    }

}
