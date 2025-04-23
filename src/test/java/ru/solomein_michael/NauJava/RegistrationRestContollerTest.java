package ru.solomein_michael.NauJava;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;
import ru.solomein_michael.NauJava.entity.Role;
import ru.solomein_michael.NauJava.entity.User;
import ru.solomein_michael.NauJava.repository.UserRepository;
import ru.solomein_michael.NauJava.service.UserService;

import java.util.List;

@SpringBootTest
public class RegistrationRestContollerTest {
    @Autowired
    private UserService userService;

    @Test
    void testGettingRegistrationPage() {
        var localhost = "http://localhost:8080";
        given().when()
                .get(localhost + "/registration")
                .then()
                .statusCode(200).contentType(MediaType.TEXT_HTML_VALUE)
                .body(containsString("registration"));
    }

    @Test
    public void testAddUser_Success() {
        var localhost = "http://localhost:8080";
        var user = new CustomUserDetails("testUser", "password123", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(localhost + "/api/register")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("user.username", equalTo("testUser"));
    }

    @Test
    public void testAddUser_UserAlreadyExists() {
        var localhost = "http://localhost:8080";
        var user = new CustomUserDetails("existingUser", "password123", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        userService.addUser(user);

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(localhost + "/register")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body("message", equalTo("An error occurred"));
    }

    @Test
    public void testAddUser_InvalidInput() {
        var localhost = "http://localhost:8080";
        given()
                .contentType("application/json")
                .body("{}") // Пустое тело
                .when()
                .post(localhost + "/register")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body("message", equalTo("An error occurred"));
    }
}
