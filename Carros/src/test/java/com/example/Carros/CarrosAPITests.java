package com.example.Carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
		//Passa endereço e Objeto que quer pegar resposta
		return rest.getForEntity(url, CarroDTO.class);
	}
	
	private ResponseEntity<List<CarroDTO>>  getCarros2(String url){
		//Passa endereço e Objeto 
		return rest.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<CarroDTO>>() {
		});
	}
	
	@Test
	public void testSave() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("Esportivo");
		
		//Insert 
		//Retorna uma responsta em response entity
		//Endereço que faz post e objeto
		ResponseEntity response = rest.postForEntity("/api/v1/carro", carro, null);
		
		//Verifica se criou
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		//Busca objeto
		String location = response.getHeaders().get("location").get(0);
		CarroDTO c = getCarros(location).getBody();
		
		assertNotNull(c);
		assertEquals("porsche", c.getNome());
		assertEquals("Espotivo", c.getNome());
		
		//Deletar objeto
		rest.delete(location);
		
		//Verfica se foi deletado
		assertEquals(HttpStatus.NOT_FOUND,getCarros(location).getStatusCode());
		
	}
	@Test
	public void testeLista() {
		List<CarroDTO> carros = getCarros2("/api/v1/carros").getBody();
		assertNotNull(carros);
		assertEquals(30,carros.size());
	}
	
	@Test
	public void testeListaTipo() {
		assertEquals(10, getCarros2("/api/v1/carros/tipo/classicos").getBody().size());
		
		assertEquals(HttpStatus.NO_CONTENT, getCarros2("/api/v1/carros/tipo/xxx").getStatusCode());
	}
	
}
