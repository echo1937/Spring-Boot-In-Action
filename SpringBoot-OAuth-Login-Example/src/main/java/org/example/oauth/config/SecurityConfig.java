package org.example.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.util.Collection;
import java.util.HashSet;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .anyRequest().authenticated();

        http.oauth2Login().loginPage("/login.html").defaultSuccessUrl("/success.html", true);
    }

    @Bean
    GrantedAuthoritiesMapper userAuthoritiesMapper() {

        return new GrantedAuthoritiesMapper() {

            @Override
            public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
                HashSet<GrantedAuthority> mappedAuthorities = new HashSet<>();
                authorities.forEach(
                        authority -> {
                            mappedAuthorities.add(authority);
                            if (authority instanceof OidcUserAuthority) {
                                OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;
                                String email = (String) oidcUserAuthority.getAttributes().get("email");

                                if (email.equals("numlock178@gmail.com")) {
                                    mappedAuthorities.add(new OidcUserAuthority("ROLE_ADMIN", oidcUserAuthority.getIdToken(), oidcUserAuthority.getUserInfo()));
                                }

                            }

                        }
                );
                return mappedAuthorities;
            }
        };
    }

}
