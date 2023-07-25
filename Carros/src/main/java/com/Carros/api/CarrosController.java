package com.Carros.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Carros.domain.Carro;
import com.Carros.domain.CarrosService;

@RestController
@RequestMapping("/api/v1/carros")

public class CarrosController {
	
	@Autowired
	private CarrosService service;
	
	@GetMapping("/api/v1/carros")
	public Iterable<Carro> get() {
		return service.getCarrosDB();
	}
}
