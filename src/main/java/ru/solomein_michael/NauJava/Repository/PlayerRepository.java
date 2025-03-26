package ru.solomein_michael.NauJava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solomein_michael.NauJava.Game.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
