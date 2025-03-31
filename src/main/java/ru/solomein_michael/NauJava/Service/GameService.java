package ru.solomein_michael.NauJava.Service;

import ru.solomein_michael.NauJava.Game.Game;

public interface GameService {
    void createGame(String gameId, String playerName);
    Game findLastByGameId(String gameId);
    void deleteByGameId(String gameId);
    Game updateGameWithPlayerMove(Long id, String direction);
}
