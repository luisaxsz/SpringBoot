package com.Carros.domain.dto;

import java.util.List;

import org.modelmapper.ModelMapper;
import com.Carros.domain.Carro;
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
