package org.gov.ce.apiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServiceApplication {

	public static void main(String[] args) {
		System.out.println("Server Running on Port ");
		SpringApplication.run(ApiServiceApplication.class, args);
	}

}
