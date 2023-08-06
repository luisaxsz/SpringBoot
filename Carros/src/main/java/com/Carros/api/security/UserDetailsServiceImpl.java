package com.Carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Carros.domain.UserRepository;

//Identificador
@Service(value="userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		//Banco de dados
		//Colocar Pacote user criado
		com.Carros.domain.User user = userRep.findByLogin(username);
		
		//verificação
		if(user==null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return User.withUsername(username).password(user.getSenha()).roles("USER").build();
		
		
		//In memory 
		/*
		if (username.equals("user")) {
			//Classe User Spring
			return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
		} else if (username.equals("admin")){
			return User.withUsername(username).password(encoder.encode("admin")).roles("ADMIN").build();
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
		*/
	
	}

	// Método build cria user e implementa userdetails

}
