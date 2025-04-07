package ru.solomein_michael.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.solomein_michael.NauJava.game.Game;

import java.util.List;

@RepositoryRestResource(path = "games")
public interface GameRepository extends JpaRepository<Game, Long> {
    public List<Game> findAllByGameId(String gameId);
    public Game findFirstByGameId(String gameId);
    public Game findLastByGameId(String gameId);
    public void deleteAllByGameId(String gameId);
}
