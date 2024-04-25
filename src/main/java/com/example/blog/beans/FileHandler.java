package com.example.blog.beans;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
public class FileHandler {
	private String baseDir;
	private boolean enableObfuscation;
	private boolean enableObfuscationHideExt;
	
	public File getStoredFile(String fileName) {
		return new File(baseDir, fileName);
	}
	
	public StoredFile storeFile(MultipartFile multipartFile) {
		String fileName = getObfuscationFileName(multipartFile.getOriginalFilename());
		
		File storePath = new File(baseDir, fileName);
		if (!storePath.getParentFile().exists()) {
			storePath.getParentFile().mkdir();
		}
		
		try {
			multipartFile.transferTo(storePath);
		} catch (IllegalStateException | IOException e) {
			return null;
		}
		
		return new StoredFile(multipartFile.getOriginalFilename(), storePath);
	}
	
	private String getObfuscationFileName(String fileName) {
		if(enableObfuscation) {
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String obfuscationName = UUID.randomUUID().toString();
			
			if (enableObfuscationHideExt) {
				return obfuscationName;
			}
			else {
				return obfuscationName + ext;
			}
		}
		
		return fileName;
	}
	
	@Getter
	public class StoredFile {
		private String fileName;
		private String realFileName;
		private String realFilePath;
		private long fileSize;
		
		StoredFile(String fileName, File storeFile) {
			this.fileName = fileName;
			this.realFileName = storeFile.getName();
			this.realFilePath = storeFile.getAbsolutePath();
			this.fileSize = storeFile.length();
		}
	}
}
