package com.pro.user_data.config;

import feign.RequestInterceptor;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignAuthConfig {

    @Bean
    public RequestInterceptor bearerInterceptor() {
        return template -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth instanceof JwtAuthenticationToken jwt) {
                String token = jwt.getToken().getTokenValue();
                template.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            }
        };
    }
}

