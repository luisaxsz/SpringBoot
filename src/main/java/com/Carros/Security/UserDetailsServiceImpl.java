package com.Carros.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Carros.User.User;
import com.Carros.User.UserRepository;



//Identificador
@Service(value = "userDetailService")

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Banco de dados
		// Colocar Pacote user criado
		User user = userRep.findByLogin(username);

		// verificação
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		// Classe User implementa inteface UserDetails então pode retornar diretamente o
		// User
		return user;

		// In memory
		/*
		if (username.equals("user")) { // Classe User Spring return
			User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
		} else if (username.equals("admin")) {
			return User.withUsername(username).password(encoder.encode("admin")).roles("ADMIN").build();
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
	*/
	// Método build cria user e implementa userdetails
	}
}
