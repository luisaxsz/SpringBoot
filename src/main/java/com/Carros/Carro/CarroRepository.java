package com.Carros.Carro;

import java.util.List;

import com.Carros.Carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

	List<Carro> findByTipo(String tipo);
	
}
