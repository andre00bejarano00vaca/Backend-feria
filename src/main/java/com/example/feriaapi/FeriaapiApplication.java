package com.example.feriaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FeriaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeriaapiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("feria-grupo82-d4r6-1ylps3aym.vercel.app").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

}
