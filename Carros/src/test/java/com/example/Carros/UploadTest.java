package com.example.Carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Carros.CarrosApplication;
import com.Carros.api.upload.FirebaseStorageService;
import com.Carros.api.upload.UploadInput;
import com.Carros.api.upload.UploadOutPut;

@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CarrosApplication.class)
public class UploadTest {
	@Autowired
	protected TestRestTemplate rest;

	@Autowired
	private FirebaseStorageService service;

	private TestRestTemplate basicAuth() {
		return rest.withBasicAuth("admin", "123");
	}
	//Preenche objeto para converter para json
	private UploadInput getUploadInput() {
		UploadInput upload = new UploadInput();
		upload.setFileName("nome.txt");
		// Base64 de Ricardo Lecheta
		upload.setBase64("UmljYXJkbyBMZWNoZXRh");
		upload.setMimeType("text/plain");
		return upload;
	}
	//Testa diretamente 
	@Test
	public void testUploadFirebase() {
		//Pega url e verifica se está ok
		String url = service.upload(getUploadInput());

		// Faz o Get na URL
		ResponseEntity<String> urlResponse = rest.getForEntity(url, String.class);
		System.out.println(urlResponse);
		assertEquals(HttpStatus.OK, urlResponse.getStatusCode());
	}

	//testa fazendo post na api
	@Test
    public void testUploadAPI() {

        UploadInput upload = getUploadInput();

        // Insert
        ResponseEntity<UploadOutPut> response = basicAuth().postForEntity("/api/v1/upload", upload, UploadOutPut.class);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.OK, response.getStatusCode());

        UploadOutPut out = response.getBody();
        assertNotNull(out);
        System.out.println(out);

        String url = out.getUrl();

        // Faz o Get na URL
        ResponseEntity<String> urlResponse = rest.getForEntity(url, String.class);
        System.out.println(urlResponse);
	}
}
