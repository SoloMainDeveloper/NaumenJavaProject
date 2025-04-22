package ru.solomein_michael.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.solomein_michael.NauJava.entity.Game;

import java.util.List;

@RepositoryRestResource(path = "games")
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByGameId(String gameId);
    Game findFirstByGameId(String gameId);
    Game findLastByGameId(String gameId);
    void deleteAllByGameId(String gameId);
}
