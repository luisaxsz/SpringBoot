package com.Carros.User;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class UserDTO {
	private String nome;
	private String email;
	private String login;

	private String token;

	public static UserDTO create(User u, String token) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO dto = modelMapper.map(u, UserDTO.class);
		dto.token = token;
		return dto;
	}
	
	public static UserDTO create(User u) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(u, UserDTO.class);
	}
	
	public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
