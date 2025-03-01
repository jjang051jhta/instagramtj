package com.jjang051.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InstagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramApplication.class, args);
	}
}
