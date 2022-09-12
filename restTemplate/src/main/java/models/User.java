package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}
