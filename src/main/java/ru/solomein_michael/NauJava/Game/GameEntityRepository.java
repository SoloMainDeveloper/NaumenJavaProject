package ru.solomein_michael.NauJava.Game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameEntityRepository extends JpaRepository<GameEntity, Long> {
    public List<GameEntity> findAllByGameId(String gameId);
    public void deleteAllByGameId(String gameId);
}
