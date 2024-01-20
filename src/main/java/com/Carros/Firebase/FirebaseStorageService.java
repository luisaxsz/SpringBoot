package com.Carros.Firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;


@Service(value = "firebaseStorageService")
public class FirebaseStorageService {
	@PostConstruct
	private void initialize() throws IOException {
		// Verifica se arquivo de conf está vazia
		if (FirebaseApp.getApps().isEmpty()) {
			FileInputStream serviceAccount =

					new FileInputStream("target/classes/serviceAccountKey.json");


			System.out.println(serviceAccount);
			System.out.println(serviceAccount);
			if (serviceAccount != null) {
				FirebaseOptions options = new FirebaseOptions.Builder()

						  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
						  .setStorageBucket("springboot-api-carros.appspot.com")
						  .build();
				FirebaseApp.initializeApp(options);
			} 
		}
	}

	public String upload(UploadInput uploadInput) {
		Bucket bucket = StorageClient.getInstance().bucket();

		System.out.println(bucket);
		// converte bytes
		byte[] bytes = Base64.getDecoder().decode(uploadInput.getBase64());
		String fileName = uploadInput.getFileName();

		// Cria arquivo no firebase storage com nome array de bytes e mimetype
		// Faz upload automaticamente
		Blob blob = bucket.create(fileName, bytes, uploadInput.getMimeType());

		// Url válida por n dias
		// URL signedUrl = blob.signUrl(3, TimeUnit.DAYS);

		// Url publica
		// Regra de permissão
		blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

		return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
	}
}
