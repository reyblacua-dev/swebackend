package com.swebackend.swebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SwebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwebackendApplication.class, args);
	}

}
