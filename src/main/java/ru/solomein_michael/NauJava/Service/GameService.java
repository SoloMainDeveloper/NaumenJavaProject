package ru.solomein_michael.NauJava.Service;

import ru.solomein_michael.NauJava.Game.GameEntity;

public interface GameService {
    void createGame(String gameId, String playerName);
    GameEntity findLastByGameId(String gameId);
    void deleteByGameId(String gameId);
    GameEntity updateGameWithPlayerMove(Long id, String direction);
}
