package com.example.Carros.Domain;

import java.util.ArrayList;
import java.util.List;

public class CarroService {
	
	public List<Carros> getCarros(){
		ArrayList<Carros> carros = new ArrayList<>();
		
		carros.add(new Carros(1L, "Fusca"));
		carros.add(new Carros(2L, "Brasilia"));
		carros.add(new Carros(3L, "Chevette"));
		
		
		return carros;
	}
	
	
	
}
