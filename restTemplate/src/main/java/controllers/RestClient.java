package controllers;

import models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestClient {

    private static final String GET_ALL_USERS_API = "http://94.198.50.185:7081/api/users";	// get
    private static final String CREATE_USER_API = "http://94.198.50.185:7081/api/users";	// post
    private static final String UPDATE_USER_API = "http://94.198.50.185:7081/api/users";	// put
    private static final String DELETE_USER_API = "http://94.198.50.185:7081/api/users/{id}";	// delete

    private static String cookies = "JSESSIONID=AD1399D2CEF750F35079A35B1764AE53; Path=/; HttpOnly";
    static RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON ));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        //get
        getAllUsersAPI(entity);
        headers.set("Cookie",cookies);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //create
        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 21);
        entity = new HttpEntity<>(newUser, headers);
        createUserAPI(entity);
        //update
        User updatedUser = new User();
        updatedUser.setId(2L);
        updatedUser.setName("Thomas");
        updatedUser.setLastName("Shelby");
        updatedUser.setAge((byte) 21);
        entity = new HttpEntity<>(updatedUser,headers);
        updateUserAPI(entity);
        //delete
        entity = new HttpEntity<>(updatedUser.getId(), headers);
        //deleteUserAPI(entity);
    }
    public static void getAllUsersAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        String cookies = response.getBody();
        System.out.println(cookies);
    }
    public static void createUserAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(CREATE_USER_API,HttpMethod.POST, entity, String.class);
        String newUserDetails = response.getBody();
        System.out.println(newUserDetails);
    }
    public static void updateUserAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(UPDATE_USER_API,HttpMethod.PUT, entity, String.class);
        String updatedUserDetail = response.getBody();
        System.out.println(updatedUserDetail);
    }
    public static void deleteUserAPI(HttpEntity<Object> entity) {
        ResponseEntity <String> response = restTemplate.exchange(DELETE_USER_API,HttpMethod.DELETE, entity, String.class);
        String deletedUser = response.getBody();
        System.out.println(deletedUser);
    }




}
