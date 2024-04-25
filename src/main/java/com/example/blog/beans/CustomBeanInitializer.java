package com.example.blog.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class CustomBeanInitializer {
	@Value("${app.multipart.base-dir:D:\\dev\\sts_workspace\\blog\\uploadfiles}")
	private String baseDir;
	@Value("${app.multipart.obfuscation.enable:false}")
	private boolean enableObfuscation;
	@Value("${app.multipart.obfuscation.hide-ext.enable:false}")
	private boolean enableObfuscationHideExt;
	
	@Bean
	public FileHandler fileHandler() {
		FileHandler fileHandler = new FileHandler();
		fileHandler.setBaseDir(baseDir);
		fileHandler.setEnableObfuscation(enableObfuscation);
		fileHandler.setEnableObfuscationHideExt(enableObfuscationHideExt);
		return fileHandler;
	}
}
