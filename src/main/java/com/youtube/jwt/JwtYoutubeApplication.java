package com.youtube.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JwtYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtYoutubeApplication.class, args);
	}

}
