package com.jdc.spring.demo.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileStorageService {
	
	@Value("${app.storage.path}")
	private String storage;

	public String save(int id, MultipartFile file) {
		
		try {
			var storagePath = Path.of(storage).resolve("profile");
			
			if(!Files.exists(storagePath, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectories(storagePath);
			}
			
			var imageName = getFileName(id, file);
			var imagePath = storagePath.resolve(imageName);
			
			Files.copy(file.getInputStream(), imagePath);
			
			return imageName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getFileName(int id, MultipartFile file) {
		return "user-%04d.%s".formatted(id, getExtension(file));
	}
	
	private String getExtension(MultipartFile file) {
		var fileName = file.getOriginalFilename();
		var array = fileName.split("\\.");
		return array[array.length - 1];
	}

}
