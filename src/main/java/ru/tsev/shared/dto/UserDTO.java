package ru.tsev.shared.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1111111111111111117L;

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    @Column(name="name", nullable = false, length=30)
    private String name;

    @Column(name="age", nullable = false)
    private int age;

    public UserDTO() {
    }

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserDTO(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}