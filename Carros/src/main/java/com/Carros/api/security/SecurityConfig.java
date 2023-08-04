package com.Carros.api.security;

import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
         .authorizeHttpRequests((authz) -> authz
        	.requestMatchers(HttpMethod.POST, "/api/v1/carros").hasRole("ADMIN")
             .anyRequest().authenticated()
         ).csrf().disable()
         .httpBasic(withDefaults());
    return http.build();
	}
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
		UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails admin = users
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build();
        UserDetails user = users
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

	/*
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

	}
	*/
}
