package ru.solomein_michael.NauJava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solomein_michael.NauJava.Game.World;

public interface WorldRepository extends JpaRepository<World, Long> {
}
