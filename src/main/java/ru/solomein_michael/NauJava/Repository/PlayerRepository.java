package ru.solomein_michael.NauJava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.solomein_michael.NauJava.Game.Player;

@RepositoryRestResource(path = "players")
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
