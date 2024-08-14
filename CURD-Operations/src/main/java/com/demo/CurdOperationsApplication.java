package com.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurdOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdOperationsApplication.class, args);
	}

//	public static

	@Bean
	public static ModelMapper getMapper(){
		return new ModelMapper();
	}
}
