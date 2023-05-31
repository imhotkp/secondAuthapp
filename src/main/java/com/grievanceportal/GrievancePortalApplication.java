package com.grievanceportal;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class GrievancePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrievancePortalApplication.class, args);
	}

}
