package com.Carros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Carros.domain.UsersService;
import com.Carros.domain.dto.UserDTO;

@Controller
@RestController
@RequestMapping("api/v1/userInfo")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getUsers() {
		return ResponseEntity.ok(service.getUsers());
	}
}
