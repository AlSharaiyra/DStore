package com.digitinary.DStore.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL;
import static org.springframework.security.core.context.SecurityContextHolder.setStrategyName;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityConfig() {

        setStrategyName(MODE_INHERITABLETHREADLOCAL);

    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http)
            throws Exception {

        // List of Swagger URLs, root page, Our public endpoints, images
        final var authWhitelist = new String[]{
                "/api-docs/**", "/swagger-ui/**", "/doc/**", "/users/register", "/db-console/**", "/**"
        };

        http
                // Enable CORS
                .cors(withDefaults())
                //Disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(STATELESS))
                .headers(headers ->
                        headers.frameOptions(FrameOptionsConfig::sameOrigin).disable())
                // Enable all whitelisted pages
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(authWhitelist).permitAll());
        return http.build();
    }
}
