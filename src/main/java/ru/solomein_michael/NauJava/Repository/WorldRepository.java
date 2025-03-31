package ru.solomein_michael.NauJava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.solomein_michael.NauJava.Game.World;

@RepositoryRestResource(path = "worlds")
public interface WorldRepository extends JpaRepository<World, Long> {
}
