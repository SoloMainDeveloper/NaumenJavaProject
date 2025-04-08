package ru.solomein_michael.NauJava.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.solomein_michael.NauJava.entity.Role;

public interface UserService {
    void addUser(UserDetails user);
    void addUser(String username, String password, Role role);
}
