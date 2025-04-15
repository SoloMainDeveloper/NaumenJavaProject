package ru.solomein_michael.NauJava.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;
import ru.solomein_michael.NauJava.service.UserService;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class RegistrationRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody CustomUserDetails user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("user", user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "An error occurred"));
        }
    }
}
