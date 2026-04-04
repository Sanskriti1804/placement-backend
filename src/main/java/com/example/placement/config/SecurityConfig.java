package com.example.placement.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration      //config file - scan this clas and register any new beans defined inside for dependency injection
public class SecurityConfig {
    @Bean
    //registers this method's return value as a spring bean
    public PasswordEncoder passwordEncoder() {
        //returns bcrypt password encoder - securely hashes pswd b4 saving it in the db
        return new BCryptPasswordEncoder();
    }

    @Bean
    //defines the main security config for HTTP req
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //csrf protection - useful for browser-based apps(cookies/ sessions)
                //disable - for api
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //configure auth rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        //testing mode: allow all endpoints without auth
                        .anyRequest().permitAll()
                )
                // disable http basic auth - (username: pswd in headers)
                .httpBasic(AbstractHttpConfigurer::disable)
                //disable form based login
                .formLogin(AbstractHttpConfigurer::disable);

        //build and return the configured security filter chain
        return http.build();
    }
}
