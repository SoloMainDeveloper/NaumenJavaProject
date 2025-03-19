package ru.solomein_michael.NauJava.Service;

import ru.solomein_michael.NauJava.Game.Game;

public interface GameService {
    void createGame(Long id, String playerName);
    Game findById(Long id);
    void deleteById(Long id);
    boolean updateGameWithPlayerMove(Long id, String direction);
}
