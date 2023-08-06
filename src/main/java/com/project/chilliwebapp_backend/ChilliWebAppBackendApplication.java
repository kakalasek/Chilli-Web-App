package com.project.chilliwebapp_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChilliWebAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChilliWebAppBackendApplication.class, args);
	}

}
