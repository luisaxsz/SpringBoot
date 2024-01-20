package com.Carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.Carros.domain.dto.*;

@Service
public class CarrosService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarrosDB() {
		List<Carro> carros = rep.findAll();
		List<CarroDTO> list = new ArrayList<>();

		for (Carro c : carros) {
			list.add(CarroDTO.create(c));
		}
		return list;
	}

	public CarroDTO getCarroById(Long id) {
		Optional<Carro> carro = rep.findById(id);
		if (carro.isPresent()) {
			// Converte carroDTO para optional
			return CarroDTO.create(carro.get());
		}

		throw new ObjectNotFoundException("Objeto não encontrado", carro);
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {
		List<Carro> carros = rep.findByTipo(tipo);
		List<CarroDTO> list = new ArrayList<>();

		for (Carro c : carros) {
			list.add(CarroDTO.create(c));
		}
		return list;
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não é possível inserir valor");
		return CarroDTO.create(rep.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");

		// Buscar carro no banco de dados
		Optional<Carro> optional = rep.findById(id);

		// Verifica se carro existe no bd
		if (optional.isPresent()) {
			Carro db = optional.get();
			// Coopia as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());

			// Atualiza carro
			rep.save(db);

			return CarroDTO.create(db);
		} else {
			throw new RuntimeException("Não foi possivel atualizar registro");
		}

	}

	public void delete(Long id) {
		rep.deleteById(id);
	}

	public List<Carro> getCarrosFake() {
		ArrayList<Carro> carros = new ArrayList<>();

		// carros.add(new Carro(1L, "Fusca"));
		// carros.add(new Carro(2L, "Brasilia"));
		// carros.add(new Carro(3L, "Chevette"));

		return carros;
	}
}
