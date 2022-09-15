package com.example.restTemplate;

import controllers.RestClient;
import models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {

	static RestClient restClient = new RestClient();

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);

		//get
		restClient.getAllUsersAPI();
		//create
		User user = new User(3L, "James", "Brown", (byte) 21);
		restClient.createUserAPI(user);
		//update
		user.setName("Thomas");
		user.setLastName("Shelby");
		restClient.updateUserAPI(user);
		//delete
		restClient.deleteUserAPI(2L);
	}
}
