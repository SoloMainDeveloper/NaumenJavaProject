package ru.solomein_michael.NauJava.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.solomein_michael.NauJava.game.*;
import ru.solomein_michael.NauJava.repository.GameRepository;
import ru.solomein_michael.NauJava.repository.PlayerRepository;
import ru.solomein_michael.NauJava.repository.WorldRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final WorldRepository worldRepository;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, WorldRepository worldRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.worldRepository = worldRepository;
    }

    @Override
    public void createGame(String gameId, String playerName) {
        var snap = gameRepository.findFirstByGameId(gameId);
        if(snap == null) {
            var player = new Player(playerName, 0, 0);
            var world = new World(new MapCell[3][3]);
            gameRepository.save(new Game(gameId, player, world));
        }
        else {
            throw new RuntimeException("Игра с таким gameId уже существует. Измените имя");
        }
    }

    @Override
    public Game findLastByGameId(String gameId) {
        var snaps = gameRepository.findAllByGameId(gameId);
        if(snaps.isEmpty())
            throw GameNotFoundException(gameId);
        return snaps.getLast();
    }

    public Game findGameSnapshotById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> GameNotFoundException(id));
    }

    private RuntimeException GameNotFoundException(String gameId) {
        return new RuntimeException("Game not found, gameId=" + gameId);
    }

    private RuntimeException GameNotFoundException(Long id) {
        return new RuntimeException("Game snapshot not found, id=" + id);
    }

    @Override
    public void deleteByGameId(String gameId) {
        gameRepository.deleteAllByGameId(gameId);
    }

    @Override
    public Game updateGameWithPlayerMove(Long id, String direction) {
        var game = findGameSnapshotById(id);
        var copy = game.deepCopy();
        var status = copy.tryMovePlayer(Direction.valueOf(direction));
        if(status){
            gameRepository.save(copy);
            return copy;
        }
        return game;
    }

    @Override
    public List<Game> findAllByGameId(String gameId){
        return gameRepository.findAllByGameId(gameId);
    }

    @Override
    public Game findFirstByGameId(String gameId){
        var game = gameRepository.findFirstByGameId(gameId);
        if (game == null) {
            throw new RuntimeException("Game not found with gameId=" + gameId);
        }
        return game;
    }

    @Override
    public List<Game> findAll(){
        return gameRepository.findAll();
    }

    @PostConstruct
    public void init(){
        System.out.println("Приложение " + appName + ", Версия " + appVersion);
    }
}

