package com.Carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.Carros.domain.dto.CarroDTO;

@Service
public class CarrosService {
	
	@Autowired
	private CarroRepository rep;
	
	public Iterable<CarroDTO> getCarrosDB(){
		List<Carro> carros = rep.findAll();
		List <CarroDTO> list = new ArrayList<>();
		
		for (Carro c : carros) {
			list.add(new CarroDTO(c));
		}
		return list;
	}
	
	public Optional<CarroDTO> getCarroById(Long id) {
		Optional <Carro> carro = rep.findById(id);
		if(carro.isPresent()) {
			return Optional.of(new CarroDTO(carro.get()));
		}else {
			return null;
		}
	}
	
	public List<CarroDTO> getCarroByTipo(String tipo) {
		List<Carro> carros = rep.findByTipo(tipo);
		List <CarroDTO> list = new ArrayList<>();
		
		for (Carro c : carros) {
			list.add(new CarroDTO(c));
		}
		return list;
	}
	
	public Carro insert(Carro carro) {		
		return rep.save(carro);	
	}
	
	public CarroDTO update(CarroDTO carro, Long id ) {
		Assert.notNull(id,"Não foi possivel atualizar o registro");
		
		//Buscar carro no banco de dados
		Optional<CarroDTO> optional = getCarroById(id);
		
		//Verifica se carro existe no bd
		if(optional.isPresent()) {
			CarroDTO db = optional.get();
			//Coopia as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			//Atualiza carro
			rep.save(db);
			
			return db;
		}else {
			throw new RuntimeException("Não foi possivel atualizar registro");
		}
		
	}
	
	public void delete(Long id) {
		Optional <CarroDTO> carro = getCarroById(id);
		if(carro.isPresent()) {
			rep.deleteById(id);
		} 
	}
	
	public List<Carro> getCarrosFake(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		//carros.add(new Carro(1L, "Fusca"));
		//carros.add(new Carro(2L, "Brasilia"));
		//carros.add(new Carro(3L, "Chevette"));
		
		return carros;
	}
}
