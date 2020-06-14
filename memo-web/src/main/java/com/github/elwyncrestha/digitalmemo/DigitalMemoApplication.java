package com.github.elwyncrestha.digitalmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.github.elwyncrestha.digitalmemo.config.CustomAuditorAware;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class DigitalMemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalMemoApplication.class, args);
	}

	@Bean
	public AuditorAware<Long> auditorProvider() {
		return new CustomAuditorAware();
	}

}
