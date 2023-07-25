package com.Carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarrosService {
	
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarrosDB(){
		return rep.findAll();
	}
	
	public Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}
	
	public List<Carro> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}
	
	public Carro insert(Carro carro) {		
		return rep.save(carro);	
	}
	
	public Carro update(Carro carro, Long id ) {
		Assert.notNull(id,"Não foi possivel atualizar o registro");
		
		//Buscar carro no banco de dados
		Optional <Carro> optional = getCarroById(id);
		
		//Verifica se carro existe no bd
		if(optional.isPresent()) {
			Carro db = optional.get();
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
		Optional <Carro> carro = getCarroById(id);
		if(carro.isPresent()) {
			rep.deleteById(id);
		} 
	}
	
	public List<Carro> getCarrosFake(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasilia"));
		carros.add(new Carro(3L, "Chevette"));
		
		return carros;
	}
}
