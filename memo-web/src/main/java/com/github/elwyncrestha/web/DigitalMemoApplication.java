package com.github.elwyncrestha.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.elwyncrestha.core.config.CustomAuditorAware;

@SpringBootApplication(scanBasePackages = "com.github.elwyncrestha")
@EnableJpaRepositories(basePackages = "com.github.elwyncrestha")
@EntityScan(basePackages = "com.github.elwyncrestha")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class DigitalMemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalMemoApplication.class, args);
	}

	@Bean(name = "auditorProvider")
	public AuditorAware<Long> auditorProvider() {
		return new CustomAuditorAware();
	}

}
