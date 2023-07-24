package com.example.Carros.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Carros.Domain.CarroService;
import com.example.Carros.Domain.Carros;

@RestController
@RequestMapping("/api/v1/carros")

public class CarrosController {
	
	private CarroService service = new CarroService();
	
	@GetMapping()
	public List<Carros> get() {
		return service.getCarros();
	}
}
