package controllers;

import models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RestClient {

    private final String GET_ALL_USERS_API = "http://94.198.50.185:7081/api/users";	// get
    private final String CREATE_USER_API = "http://94.198.50.185:7081/api/users";	// post
    private final String UPDATE_USER_API = "http://94.198.50.185:7081/api/users";	// put
    private final String DELETE_USER_API = "http://94.198.50.185:7081/api/users/{id}";	// delete
    RestTemplate restTemplate = new RestTemplate();
    private List<String> cookies;

    public void getAllUsersAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity <String> response = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
        cookies = response.getHeaders().get("Set-Cookie");
        System.out.println(cookies);
        System.out.println(response);
    }

    public void createUserAPI(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity <String> response = restTemplate.exchange(CREATE_USER_API,   HttpMethod.POST, entity, String.class);
        String newUserDetails = response.getBody();
        System.out.println(newUserDetails + " - 1st piece of code");
    }

    public void updateUserAPI(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity <String> response = restTemplate.exchange(UPDATE_USER_API,    HttpMethod.PUT, entity, String.class);
        String updatedUserDetails = response.getBody();
        System.out.println(updatedUserDetails + " - 2nd piece of code");
    }

    public void deleteUserAPI(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity <String> response = restTemplate.exchange(DELETE_USER_API,   HttpMethod.DELETE, entity, String.class, id);
        String deletedUser = response.getBody();
        System.out.println(deletedUser + " - 3rd piece of code");
    }
}
