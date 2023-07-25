package com.Carros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Carros.domain.dto.CarroDTO;



@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

	List<Carro> findByTipo(String tipo);

	void save(CarroDTO db);
	
}
