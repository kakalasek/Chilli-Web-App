package com.project.chilliwebapp_backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class ChilliWebAppBackendApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(ChilliWebAppBackendApplication.class, args);
	}

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

}
