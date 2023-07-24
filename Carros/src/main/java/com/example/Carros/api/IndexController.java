package com.example.Carros.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class IndexController {
	
	//Post - Mais recomendado
	@GetMapping("/login")
	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return " login:  " + login + "  senha: " + senha;
	}
	
	@PostMapping()
	public String post() {
		return "Post SpringBoot";
	}
	
	@PutMapping()
	public String put() {
		return "PUT SpringBoot";
	}
	
	@DeleteMapping()
	public String delete() {
		return "Delete SpringBoot";
	}
	
}

