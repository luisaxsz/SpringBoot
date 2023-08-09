package com.Carros.api.security.jwt;

import lombok.Data;

@Data
public class JwtLoginInput {
	private String username;
	private String password;
}
