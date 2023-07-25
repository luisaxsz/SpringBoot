package com.Carros.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component

public interface CarroRepository extends CrudRepository<Carro,Long> {
	
}
