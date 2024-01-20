package com.Carros.Security.jwt;

import lombok.Data;

@Data
public class JwtLoginInput {
	private String username;
	private String password;
}
