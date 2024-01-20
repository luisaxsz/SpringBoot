package com.Carros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.Carros")
public class CarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
	}

}
