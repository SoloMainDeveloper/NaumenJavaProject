package ru.solomein_michael.NauJava.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.solomein_michael.NauJava.Game.*;
import ru.solomein_michael.NauJava.Repository.GameRepository;
import ru.solomein_michael.NauJava.Repository.PlayerRepository;
import ru.solomein_michael.NauJava.Repository.WorldRepository;
//import ru.solomein_michael.NauJava.Repository.GameRepository;


@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameEntityRepository;
    private final PlayerRepository playerRepository;
    private final WorldRepository worldRepository;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public GameServiceImpl(GameRepository gameEntityRepository, PlayerRepository playerRepository, WorldRepository worldRepository) {
        this.gameEntityRepository = gameEntityRepository;
        this.playerRepository = playerRepository;
        this.worldRepository = worldRepository;
    }

    @Override
    public void createGame(String gameId, String playerName) {
        var snap = gameEntityRepository.findFirstByGameId(gameId);
        if(snap == null) {
            var player = new Player(playerName, 0, 0);
            var world = new World(new MapCell[3][3]);
            gameEntityRepository.save(new Game(gameId, player, world));
        }
        else {
            throw new RuntimeException("Игра с таким gameId уже существует. Измените имя");
        }
    }

    @Override
    public Game findLastByGameId(String gameId) {
        var snaps = gameEntityRepository.findAllByGameId(gameId);
        if(snaps.isEmpty())
            throw GameNotFoundException(gameId);
        return snaps.getLast();
    }

    public Game findGameSnapshotById(Long id) {
        return gameEntityRepository.findById(id).orElseThrow(() -> GameNotFoundException(id));
    }

    private RuntimeException GameNotFoundException(String gameId) {
        return new RuntimeException("Game not found, gameId=" + gameId);
    }

    private RuntimeException GameNotFoundException(Long id) {
        return new RuntimeException("Game snapshot not found, id=" + id);
    }

    @Override
    public void deleteByGameId(String gameId) {
        gameEntityRepository.deleteAllByGameId(gameId);
    }

    @Override
    public Game updateGameWithPlayerMove(Long id, String direction) {
        var game = findGameSnapshotById(id);
        var copy = game.deepCopy();
        var status = copy.tryMovePlayer(Direction.valueOf(direction));
        if(status){
            gameEntityRepository.save(copy);
            return copy;
        }
        return game;
    }

    @PostConstruct
    public void init(){
        System.out.println("Приложение " + appName + ", Версия " + appVersion);
    }
}

