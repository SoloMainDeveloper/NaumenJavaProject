package ru.solomein_michael.NauJava.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();

    public User(){
    }

    public User(String username, String password, Role initialRole){
        this.username = username;
        this.password = password;
        roles.add(initialRole);
    }

    public List<Role> getRoles(){
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }
}
