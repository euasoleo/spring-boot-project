package com.assignment.restservice.assignmentrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.assignment.restservice.assignmentrestservice.property.FileStorageProperties;

@SpringBootApplication

@EnableConfigurationProperties({
    FileStorageProperties.class
})

public class AssignmentRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentRestServiceApplication.class, args);
	}

}

