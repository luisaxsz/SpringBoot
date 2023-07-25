package com.Carros.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CarroRepository extends CrudRepository<Carro,Long> {

	Iterable<Carro> findByTipo(String tipo);
	
}
