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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.Carros.CarrosApplication;
import com.Carros.domain.Carro;
import com.Carros.domain.CarrosService;
import com.Carros.domain.dto.CarroDTO;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CarrosApplication.class)
public class CarrosApplicationTests {
	
	@Autowired
	private CarrosService service;

	@Test
	public void test1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("Luxo");
		
		CarroDTO c = service.insert(carro);
		
		assertNotNull(c);
		
		Long id = c.getId();
		assertNotNull(id);
		
		//Buscar Objeto
		Optional <CarroDTO> op = service.getCarroById(id);
		assertTrue(op.isPresent());
		
		c = op.get();
		
		assertEquals("Ferrari", c.getNome());
		assertEquals("Luxo", c.getTipo());
		
		//Deletar objeto
		service.delete(id);
		
		//Verificar se deletou
		//assertFalse(service.getCarroById(id).isPresent());
	}
	
	@Test
	public void testeLista() {
		List<CarroDTO> carros =  service.getCarrosDB();
		//Nem sempre vai funcionar -> pois muda toda hora
		assertEquals(30, carros.size());
		
	}
	
	@Test
	public void get() {
		Optional<CarroDTO> carro = service.getCarroById(11L);
		assertTrue(carro.isPresent());
		
		CarroDTO c = carro.get();
		
		assertEquals("Ferrari FF", c.getNome());
		
	}
	
	@Test
	public void getTipo() {
		
		
		assertEquals(10,service.getCarroByTipo("Classicos").size());
		assertEquals(10,service.getCarroByTipo("Esportivos").size());
		assertEquals(10,service.getCarroByTipo("Luxo").size());
		
		
	}


}
