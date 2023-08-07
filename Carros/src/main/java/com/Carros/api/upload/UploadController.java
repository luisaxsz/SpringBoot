package com.Carros.api.upload;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Carros.domain.upload.FirebaseStorageService;
import com.Carros.domain.upload.UploadInput;
import com.Carros.domain.upload.UploadOutPut;

@RestController
@RequestMapping("api/v1/upload")
@ComponentScan(basePackages = {"com.Carros"})
public class UploadController {
	@Autowired
	@Qualifier("firebaseStorageService")
	private FirebaseStorageService uploadService;

//	@PostMapping("/upload")
//	public ResponseEntity upload(@RequestParam String fileName, @RequestParam String base64) {
//		String s = "Filename: " + fileName + ">>Base64 > " + base64;
//
//		return ResponseEntity.ok().build();

//}
	//recebe json como parametro
	@PostMapping
	public ResponseEntity<?> upload(@RequestBody UploadInput uploadInput) throws IOException{
		String url = uploadService.upload(uploadInput);
		return ResponseEntity.ok(new UploadOutPut(url));
	}
		
	
}
