package ru.solomein_michael.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;
import ru.solomein_michael.NauJava.entity.Role;
import ru.solomein_michael.NauJava.entity.User;
import ru.solomein_michael.NauJava.repository.UserRepository;
import ru.solomein_michael.NauJava.service.UserService;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser_Success() {
        UserDetails userDetails = new CustomUserDetails("testUser", "password", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        when(userRepository.findByUsername("testUser")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.addUser(userDetails);

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testAddUser_UserAlreadyExists() {
        UserDetails userDetails = new CustomUserDetails("existingUser", "password", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        when(userRepository.findByUsername("existingUser")).thenReturn(new User("existingUser", "encodedPassword", Role.USER));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.addUser(userDetails);
        });

        assertEquals("User with username = 'existingUser' already exists", exception.getMessage());
    }

    @Test
    public void testGetCount() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(new User("user1", "password", Role.USER)));

        int count = userService.getCount();

        assertEquals(1, count);
    }
}
