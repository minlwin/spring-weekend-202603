package com.jdc.demo.model.service;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileImageService {
	
	@Value("${app.storage.path}")
	private String storage;

	public String upload(int id, MultipartFile file) {
		try {
			var storagePath = Path.of(storage);
			if(!Files.exists(storagePath, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectories(storagePath);
			}
			
			var imageFileName = imageFileName(id, file);
			
			var imageFile = storagePath.resolve(imageFileName);
			
			Files.copy(file.getInputStream(), imageFile, StandardCopyOption.REPLACE_EXISTING);
			
			return imageFileName;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String imageFileName(int id, MultipartFile file) {
		
		var array = file.getOriginalFilename().split("\\.");
		var extension = array[array.length -1];
		
		return "profile-%06d.%s".formatted(id, extension);
	}

}
