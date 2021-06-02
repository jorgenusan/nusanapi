package com.nusan.nusanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class NusanapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NusanapiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			// More info about CORS: https://spring.io/guides/gs/rest-service-cors/
			// And: https://stackoverflow.com/questions/37980914/spring-global-cors-configuration-not-working-but-controller-level-config-does
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
						.maxAge(-1)   // add maxAge
						.allowCredentials(false);
			}
		};
	}
}
