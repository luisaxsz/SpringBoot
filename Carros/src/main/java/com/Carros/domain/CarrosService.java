package com.Carros.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrosService {
	
	@Autowired
	private CarroRepository rep;

	public Iterable<Carro> getCarrosDB(){
		return rep.findAll();
	}
	
	public List<Carro> getCarrosFake(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasilia"));
		carros.add(new Carro(3L, "Chevette"));
		
		return carros;
	}
}
