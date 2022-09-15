package models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Component
@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User(Long id, String name, String lastName, Byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
}
