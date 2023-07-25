package com.Carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Carros.domain.Carro;
import com.Carros.domain.CarrosService;

@Controller
@RestController
@RequestMapping("/api/v1/carros")

public class CarrosController {
	
	@Autowired
	private CarrosService service;
	
	@GetMapping()
	public Iterable<Carro> get() {
		return service.getCarrosDB();
	}
	
	@GetMapping("{id}")
	public Optional<Carro> get(@PathVariable("id") Long id){
		return service.getCarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> get(@PathVariable("tipo") String tipo){
		return service.getCarroByTipo(tipo);
	}
}
