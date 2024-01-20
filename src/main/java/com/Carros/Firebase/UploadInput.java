package com.Carros.Firebase;

import lombok.Data;

@Data
public class UploadInput {
	private String fileName;
	private String base64;
	private String mimeType;
}
