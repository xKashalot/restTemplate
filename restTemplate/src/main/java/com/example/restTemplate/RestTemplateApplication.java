package com.example.restTemplate;

import controllers.RestClient;
import models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);

		//get
		RestClient.getAllUsersAPI();
		//create
		User user = new User(3L, "James", "Brown", (byte) 21);
		RestClient.createUserAPI(user);
		//update
		user.setName("Thomas");
		user.setLastName("Shelby");
		RestClient.updateUserAPI(user);
		//delete
		RestClient.deleteUserAPI(2L);
	}
}
