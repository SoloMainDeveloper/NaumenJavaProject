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
    public void createGame(String gameId, String playerName) {
        var snap = gameRepository.read(gameId);
        if(snap.isEmpty()) {
            gameRepository.create(new Game(gameId, playerName));
        }
        else {
            throw new RuntimeException("Игра с таким gameId уже существует. Измените имя");
        }
    }

    @Override
    public Game findLastByGameId(String gameId) {
        return gameRepository.read(gameId).orElseThrow(() -> GameNotFoundException(gameId));
    }

    public Game findGameSnapshotById(Long id) {
        return gameRepository.read(id).orElseThrow(() -> GameNotFoundException(id));
    }

    private RuntimeException GameNotFoundException(String gameId) {
        return new RuntimeException("Game not found, gameId=" + gameId);
    }

    private RuntimeException GameNotFoundException(Long id) {
        return new RuntimeException("Game snapshot with id=" + id + " not found");
    }

    @Override
    public void deleteByGameId(String gameId) {
        gameRepository.deleteByGameId(gameId);
    }

    public void deleteGameSnapshotById(Long id){
        gameRepository.deleteGameSnapshot(id);
    }

    @Override
    public Game updateGameWithPlayerMove(Long id, String direction) {
        var game = findGameSnapshotById(id);
        var copy = game.deepCopy();
        var status = copy.tryMovePlayer(Game.Direction.valueOf(direction));
        if(status){
            gameRepository.update(copy);
            return copy;
        }
        return game;
    }

    @PostConstruct
    public void init(){
        System.out.println("Приложение " + appName + ", Версия " + appVersion);
    }
}

