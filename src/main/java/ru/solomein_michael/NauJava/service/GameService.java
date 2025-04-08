package ru.solomein_michael.NauJava.service;

import ru.solomein_michael.NauJava.entity.Game;

import java.util.List;

public interface GameService {
    void createGame(String gameId, String playerName);
    Game findLastByGameId(String gameId);
    void deleteByGameId(String gameId);
    Game updateGameWithPlayerMove(Long id, String direction);
    List<Game> findAllByGameId(String gameId);
    Game findFirstByGameId(String gameId);
    List<Game> findAll();
}
