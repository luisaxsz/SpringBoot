package com.Carros.domain.dto;

import com.Carros.domain.Carro;

import lombok.*;

@Data
public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;
	
	public CarroDTO(Carro c ) {
		this.id = c.getId();
	}
}
