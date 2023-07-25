package com.Carros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CarroRepository extends CrudRepository<Carro,Long> {

	List<Carro> findByTipo(String tipo);
	
}
