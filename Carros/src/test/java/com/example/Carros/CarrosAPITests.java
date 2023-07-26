package com.example.Carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Carros.CarrosApplication;
import com.Carros.domain.Carro;
import com.Carros.domain.dto.CarroDTO;

@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CarrosApplication.class)
public class CarrosAPITests {
	
	//Faz requisições
	@Autowired
	protected TestRestTemplate rest;
	
	private ResponseEntity<CarroDTO>  getCarros(String url){
		//Passa endereço e Objeto 
		return rest.getForEntity(url, CarroDTO.class);
	}
	
	private ResponseEntity<List<CarroDTO>>  getCarros2(String url){
		//Passa endereço e Objeto 
		return rest.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<CarroDTO>>() {
		});
	}
	
	
}
