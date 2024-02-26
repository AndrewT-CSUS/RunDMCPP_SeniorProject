package com.RunDMCPP.Backend;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.authorizeHttpRequests(request -> request.
                    requestMatchers(HttpMethod.POST).authenticated().
                    requestMatchers(HttpMethod.PUT).authenticated().
                    requestMatchers(HttpMethod.DELETE).authenticated().
                    requestMatchers(HttpMethod.GET).permitAll())
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                )
                ;
        return httpSecurity.build();
    }
}
