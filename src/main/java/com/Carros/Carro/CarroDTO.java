package com.Carros.Carro;

import org.modelmapper.ModelMapper;
import lombok.*;

@Data
public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;

	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
}
