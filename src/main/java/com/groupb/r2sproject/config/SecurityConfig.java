package com.groupb.r2sproject.config;

import com.groupb.r2sproject.shared.filters.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorization -> authorization
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                                .requestMatchers("/variant-products/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/categories/**").permitAll()
                                .requestMatchers("/orders/**").hasRole("USER")
                                .requestMatchers("/cart-lines/**").hasRole("USER")
                                .requestMatchers("/addresses/**").hasRole("USER")
//                                .requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/variant-products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/variant-products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/carts/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/categories/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
