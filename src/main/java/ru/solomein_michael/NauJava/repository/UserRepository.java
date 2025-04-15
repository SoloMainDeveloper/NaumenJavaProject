package ru.solomein_michael.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solomein_michael.NauJava.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
