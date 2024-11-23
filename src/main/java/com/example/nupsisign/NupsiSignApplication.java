package com.example.nupsisign;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.nupsisign.FileUpload.IFileUploadService;

@SpringBootApplication
public class NupsiSignApplication implements CommandLineRunner {

	@Resource
	private IFileUploadService fileUploadService;

	public static void main(String[] args)
	{
		SpringApplication.run(NupsiSignApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileUploadService.init();
	}

}
