package com.Carros.api.security.jwt;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

;

@Component
public class JwtAuthFilter extends BasicAuthenticationFilter {
	private static Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

	private UserDetailsService userDetailsService;

	public JwtAuthFilter(AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");

		if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
			// Não informou o authorization
			filterChain.doFilter(request, response);
		}

		try {

			if (!JwtUtil.isTokenValid(token)) {
				throw new AccessDeniedException("Acesso negado.");
			}

			String login = JwtUtil.getLogin(token);

			UserDetails userDetails = userDetailsService.loadUserByUsername(login);

			List<GrantedAuthority> authorities = JwtUtil.getRoles(token);

			// var authorities = ((UserDetails) userDetails).getAuthorities();

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
					authorities);

			// Salva o Authentication no contexto do Spring
			// indica se usuário está autenticado 
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(request, response);

		} catch (RuntimeException ex) {
			logger.error("Authentication error: " + ex.getMessage(), ex);

			throw ex;
		}
	}

}
