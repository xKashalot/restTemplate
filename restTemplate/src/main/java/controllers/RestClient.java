package controllers;

import models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

public class RestClient {

    private final String USERS_API = "http://94.198.50.185:7081/api/users";	// get

    RestTemplate restTemplate = new RestTemplate();
    private List<String> cookies;

    public void getAllUsersAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity <String> response = restTemplate.exchange(USERS_API, HttpMethod.GET, entity, String.class);
        cookies = response.getHeaders().get("Set-Cookie");
        System.out.println(cookies);
        System.out.println(response);
    }

    public void createUserAPI(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity <String> response = restTemplate.exchange(USERS_API,   HttpMethod.POST, entity, String.class);
        String newUserDetails = response.getBody();
        System.out.println(newUserDetails + " - 1st piece of code");
    }

    public void updateUserAPI(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity <String> response = restTemplate.exchange(USERS_API,    HttpMethod.PUT, entity, String.class);
        String updatedUserDetails = response.getBody();
        System.out.println(updatedUserDetails + " - 2nd piece of code");
    }

    public void deleteUserAPI(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity <String> response = restTemplate.exchange(USERS_API + "/" + id,   HttpMethod.DELETE, entity, String.class);
        String deletedUser = response.getBody();
        System.out.println(deletedUser + " - 3rd piece of code");
    }
}
