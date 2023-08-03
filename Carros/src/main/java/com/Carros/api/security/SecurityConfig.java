package com.Carros.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        	 .requestMatchers("/api/v1/carros").hasRole("ADMIN")
             .anyRequest().authenticated()
         )
         .httpBasic();
    return http.getOrBuild();
	}
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();       
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("ADMIN")
            .password(encoder.encode("ADMIN"))
            .roles("ADMIN")
            .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("USER")
                .password(encoder.encode("USER"))
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
