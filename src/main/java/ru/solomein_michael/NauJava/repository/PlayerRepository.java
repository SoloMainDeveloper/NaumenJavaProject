package ru.solomein_michael.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.solomein_michael.NauJava.entity.Player;

@RepositoryRestResource(path = "players")
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
