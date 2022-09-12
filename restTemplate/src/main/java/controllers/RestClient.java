package controllers;

import models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RestClient {

    private static final String GET_ALL_USERS_API = "http://94.198.50.185:7081/api/users";	// get
    private static final String CREATE_USER_API = "http://94.198.50.185:7081/api/users";	// post
    private static final String UPDATE_USER_API = "http://94.198.50.185:7081/api/users";	// put
    private static final String DELETE_USER_API = "http://94.198.50.185:7081/api/users/{id}";	// delete

    private static String cookies = "JSESSIONID=AD1399D2CEF750F35079A35B1764AE53; Path=/; HttpOnly";
    static RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        getAllUsersAPI();
        createUserAPI();
        //updateUserAPI();
        deleteUserAPI();
    }

    //Получить список всех пользователей
    //Когда вы получите ответ на свой первый запрос, вы должны сохранить свой session id, который получен через cookie.Вы получите его в заголовке ответа set-cookie.
    // Поскольку все действия происходят в рамках одной сессии, все дальнейшие запросы должны использовать полученный session id ( необходимо использовать заголовок в последующих запросах )
    public static void getAllUsersAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON ));
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity <String> response = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        String cookies = response.getBody();
        System.out.println(cookies);
    }

    //Сохранить пользователя с id = 3, name = James, lastName = Brown, age = на ваш выбор. В случае успеха вы получите первую часть кода.
    public static void createUserAPI() {
        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 21);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(newUser, headers);
        ResponseEntity <String> response = restTemplate.exchange(CREATE_USER_API,HttpMethod.POST, entity, String.class);
        String newUserDetails = response.getBody();
        System.out.println(newUserDetails);
    }

    //Изменить пользователя с id = 3. Необходимо поменять name на Thomas, а lastName на Shelby. В случае успеха вы получите еще одну часть кода.
    public static void updateUserAPI() {
        User updatedUser = new User();
        updatedUser.setId(3L);
        updatedUser.setName("Thomas");
        updatedUser.setLastName("Shelby");
        updatedUser.setAge((byte) 21);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(updatedUser, headers);
        ResponseEntity <String> response = restTemplate.exchange(UPDATE_USER_API,HttpMethod.PUT, entity, String.class);
        String updatedUserDetail = response.getBody();
        System.out.println(updatedUserDetail);
    }

    //Удалить пользователя с id = 3. В случае успеха вы получите последнюю часть кода.
    public static void deleteUserAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>("2", headers);
        ResponseEntity <String> response = restTemplate.exchange(DELETE_USER_API,HttpMethod.DELETE, entity, String.class);
        String deletedUser = response.getBody();
        System.out.println(deletedUser);
    }




}
