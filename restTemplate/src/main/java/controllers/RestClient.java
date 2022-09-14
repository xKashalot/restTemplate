package controllers;

import models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    private static final String GET_ALL_USERS_API = "http://94.198.50.185:7081/api/users";	// get
    private static final String CREATE_USER_API = "http://94.198.50.185:7081/api/users";	// post
    private static final String UPDATE_USER_API = "http://94.198.50.185:7081/api/users";	// put
    private static final String DELETE_USER_API = "http://94.198.50.185:7081/api/users/{id}";	// delete
    static RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        //get
        getAllUsersAPI(entity);
        //create
        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 21);
        entity = new HttpEntity<>(newUser, headers);
        createUserAPI(entity);
        //update
        newUser.setId(2L);
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        newUser.setAge((byte) 21);
        entity = new HttpEntity<>(newUser, headers);
        updateUserAPI(entity);
        //delete
        entity = new HttpEntity<>(newUser, headers);
        deleteUserAPI(entity, 2L);
    }
    public static void getAllUsersAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        String result = response.getBody();
        System.out.println(result);
    }
    public static void createUserAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(CREATE_USER_API,   HttpMethod.POST, entity, String.class);
        String newUserDetails = response.getBody();
        System.out.println(newUserDetails);
    }
    public static void updateUserAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(UPDATE_USER_API,    HttpMethod.PUT, entity, String.class);
        String updatedUserDetails = response.getBody();
        System.out.println(updatedUserDetails);
    }
    public static void deleteUserAPI(HttpEntity<Object> entity, Long id) {
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        ResponseEntity <String> response = restTemplate.exchange(DELETE_USER_API,   HttpMethod.DELETE, entity, String.class, id);
        String deletedUser = response.getBody();
        System.out.println(deletedUser);
    }
}
