package com.example.gugucoding_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GugucodingBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GugucodingBootApplication.class, args);
	}

}
