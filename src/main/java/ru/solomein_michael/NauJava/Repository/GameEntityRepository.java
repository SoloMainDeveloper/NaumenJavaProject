package ru.solomein_michael.NauJava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solomein_michael.NauJava.Game.GameEntity;

import java.util.List;

public interface GameEntityRepository extends JpaRepository<GameEntity, Long> {
    public List<GameEntity> findAllByGameId(String gameId);
    public GameEntity findOneByGameId(String gameId);
    public void deleteAllByGameId(String gameId);
}
