package com.Carros.api.security.jwt;

import com.Carros.domain.User;
import com.Carros.domain.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;


public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	public static final String AUTH_URL = "/api/v1/login";
	
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // api/authenticate
        setFilterProcessesUrl(AUTH_URL);
    }

	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest request,
	                                                HttpServletResponse response) {

	        try {
	            JwtLoginInput login = new ObjectMapper().readValue(request.getInputStream(), JwtLoginInput.class);
	            String username = login.getUsername();
	            String password = login.getPassword();

	            if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
	                throw new BadCredentialsException("Invalid username/password.");
	            }

	            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

	            return authenticationManager.authenticate(auth);
	        } catch (IOException e) {
	            throw new BadCredentialsException(e.getMessage());
	        }
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	                                            FilterChain filterChain, Authentication authentication) throws IOException {
	        User user = (User) authentication.getPrincipal();

	        String jwtToken = JwtUtil.createToken(user);

//	        String json = ServletUtil.getJson("token", jwtToken);
	        String json = UserDTO.create(user, jwtToken).toJson();
	        ServeletUtil.write(response, HttpStatus.OK, json);
	    }

	    @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException error) throws IOException, ServletException {

	        String json = ServeletUtil.getJson("error", "Login incorreto");
	        ServeletUtil.write(response, HttpStatus.UNAUTHORIZED, json);
	    }

}
