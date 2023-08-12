package com.Carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Carros.domain.Carro;
import com.Carros.domain.CarrosService;
import com.Carros.domain.dto.CarroDTO;


@Controller
@RestController // Caracteristica do controller
@RequestMapping("/api/v1/carros") // Nome do recurso

public class CarrosController {

	@Autowired
	private CarrosService service;
	
	//Representação do recurso -> mensagem de retorno
	@GetMapping()
	public ResponseEntity<Iterable<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarrosDB());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		CarroDTO carro = service.getCarroById(id);

		return ResponseEntity.ok(carro);

		// lambdas
		// return carro.map(c ->
		// ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> get(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarroByTipo(tipo);
		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros);
		}
	}

	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> post(@RequestBody Carro carro) {
		CarroDTO c = service.insert(carro);
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
	}

	// Montando url até o caminho /id para recurso ->
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		CarroDTO c = service.update(carro, id);
		if (c != null) {
			return ResponseEntity.ok(c);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();

		
	}
}
