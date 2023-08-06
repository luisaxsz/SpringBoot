package com.Carros.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Identificador
@Service(value="userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		if (username.equals("user")) {
			return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
		} else {
			return User.withUsername(username).password(encoder.encode("admin")).roles("ADMIN").build();

		}

	
	}

	// Método build cria user e implementa userdetails

}
