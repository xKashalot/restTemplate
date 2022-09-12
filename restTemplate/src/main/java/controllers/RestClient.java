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
    private static final String GET_USER_BY_ID_API = "http://94.198.50.185:7081/api/users/{id}";	// get
    static RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        getAllUsersAPI();
        createUserAPI();
        updateUserAPI();
        deleteUserAPI();
    }

    //Получить список всех пользователей
    //Когда вы получите ответ на свой первый запрос, вы должны сохранить свой session id, который получен через cookie.Вы получите его в заголовке ответа set-cookie.
    // Поскольку все действия происходят в рамках одной сессии, все дальнейшие запросы должны использовать полученный session id ( необходимо использовать заголовок в последующих запросах )
    public static void getAllUsersAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity <String> response = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
        response.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        String result = response.getBody();
        System.out.println(result);
    }

    //Сохранить пользователя с id = 3, name = James, lastName = Brown, age = на ваш выбор. В случае успеха вы получите первую часть кода.
    public static void createUserAPI() {
        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 21);
        HttpHeaders headers = new HttpHeaders();
        //set cookie
        HttpEntity<User> entity = new HttpEntity<>(newUser);
        ResponseEntity <User> response = restTemplate.postForEntity(CREATE_USER_API, entity, User.class);
        System.out.println("Status: " + response.getStatusCode() );
        if (response.getStatusCode() == HttpStatus.OK) {
            User e = response.getBody();
            System.out.println("(Client Side) User Created: "+ e.getId());
        }
    }

    //Изменить пользователя с id = 3. Необходимо поменять name на Thomas, а lastName на Shelby. В случае успеха вы получите еще одну часть кода.
    public static void updateUserAPI() {
        User updatedUser = new User();
        updatedUser.setId(3L);
        updatedUser.setName("Thomas");
        updatedUser.setLastName("Shelby");
        updatedUser.setAge((byte) 21);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<User> body = new HttpEntity<>(updatedUser, headers);
        restTemplate.put(UPDATE_USER_API, body, new Object(){});
    }

    //Удалить пользователя с id = 3. В случае успеха вы получите последнюю часть кода.
    public static void deleteUserAPI() {
        Object[] uriValue = new Object[] {"3"};
        restTemplate.delete(DELETE_USER_API, uriValue);
        System.out.println("User deleted");
    }




}
