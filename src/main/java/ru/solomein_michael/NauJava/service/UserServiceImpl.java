package ru.solomein_michael.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.solomein_michael.NauJava.entity.Role;
import ru.solomein_michael.NauJava.entity.User;
import ru.solomein_michael.NauJava.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDetails user) {
        if(userRepository.findByUsername(user.getUsername()) != null)
            return;
        userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), Role.USER));
    }

    @Override
    public void addUser(String username, String password, Role role) {
        if(userRepository.findByUsername(username) != null)
            return;
        userRepository.save(new User(username, password, role));
    }
}
