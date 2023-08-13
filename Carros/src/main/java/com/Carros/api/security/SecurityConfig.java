package com.Carros.api.security;

import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.Carros.api.security.jwt.JwtAuthenticationFilter;
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
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).build();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManager authManager = authenticationManager(http);
		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers(HttpMethod.GET, "api/v1/login").permitAll())
				.csrf().disable();
		
		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated());

		http.exceptionHandling(handling -> handling.accessDeniedHandler(accessDeniedHandler)
						.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilter(new JwtAuthenticationFilter(authManager))
				.addFilter(new JwtAuthFilter(authManager, userDetailService));
		
		return http.build();
	}

	protected void userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

		/*
		 * UserBuilder users = User.withDefaultPasswordEncoder(); UserDetails admin =
		 * users.username("admin").password("admin").roles("ADMIN").build(); UserDetails
		 * user = users.username("user").password("user").roles("USER").build(); return
		 * new InMemoryUserDetailsManager(admin, user);
		 */

	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
