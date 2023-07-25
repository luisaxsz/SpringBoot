package com.Carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Iterable<Carro>>  get() {
		return ResponseEntity.ok(service.getCarrosDB());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Carro>get(@PathVariable("id") Long id){
		Optional<Carro> carro = service.getCarroById(id);
		/*
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		}else {
			return ResponseEntity.notFound().build();
		}*/
		
		//lambdas
		return carro.map(c -> ResponseEntity.ok(c))
			.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity get(@PathVariable("tipo") String tipo){
		List<Carro> carros = service.getCarroByTipo(tipo);
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(carros);
		}
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
				
		return "Carro salvo com sucesso: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable ("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		
		return "Carro atualizado com suscesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return "Carro deletado com sucesso";
	}
	
}
