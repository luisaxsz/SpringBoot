package com.Carros.api.security;

import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.Carros.api.security.jwt.JwtAthenticationFilter;
import com.Carros.api.security.jwt.JwtAuthFilter;
import com.Carros.api.security.jwt.handler.AccessDeniedHandler;
import com.Carros.api.security.jwt.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	// identificador
	@Qualifier("userDetailService")
	private UserDetailsService userDetailService;

	@Autowired
	private UnauthorizedHandler unauthorizedHandler;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Bean
	protected void filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()
				.requestMatchers(HttpMethod.GET, "api/v1/login").permitAll()).csrf().disable()
				.addFilterAfter(new JwtAthenticationFilter(null), JwtAthenticationFilter.class)
				.addFilterAfter(new JwtAuthFilter(null, null), JwtAuthFilter.class).exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	protected void userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
		
		/*
		UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails admin = users.username("admin").password("admin").roles("ADMIN").build();
		UserDetails user = users.username("user").password("user").roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
		*/

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
