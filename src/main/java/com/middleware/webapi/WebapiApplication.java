package com.middleware.webapi;


import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.middleware.webapi.fileUpload.services.FilesStorageService;

@SpringBootApplication
public class WebapiApplication implements CommandLineRunner {
	 @Resource
	  FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(WebapiApplication.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }

}
