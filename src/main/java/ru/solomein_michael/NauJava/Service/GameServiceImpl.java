package ru.solomein_michael.NauJava.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.solomein_michael.NauJava.Game.Game;
import ru.solomein_michael.NauJava.Repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void createGame(Long id, String playerName) {
        var game = new Game(id, playerName);
        gameRepository.create(game);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.delete(id);
    }

    @Override
    public boolean updateGameWithPlayerMove(Long id, String direction) {
        var game = findById(id);
        var status = game.tryMovePlayer(Game.Direction.valueOf(direction));
        gameRepository.update(game);
        return status;
    }

    @PostConstruct
    public void init(){
        System.out.println("Приложение " + appName + ", Версия " + appVersion);
    }
}

