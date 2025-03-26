package ru.solomein_michael.NauJava.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.solomein_michael.NauJava.Game.*;
//import ru.solomein_michael.NauJava.Repository.GameRepository;

import java.util.Comparator;

@Service
public class GameServiceImpl implements GameService {
    private final GameEntityRepository gameEntityRepository;
    private final PlayerRepository playerRepository;
    private final WorldRepository worldRepository;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public GameServiceImpl(GameEntityRepository gameEntityRepository, PlayerRepository playerRepository, WorldRepository worldRepository) {
        this.gameEntityRepository = gameEntityRepository;
        this.playerRepository = playerRepository;
        this.worldRepository = worldRepository;
    }

    @Override
    public void createGame(String gameId, String playerName) {
        var player = new Player(playerName, 0, 0);
        playerRepository.save(player);
        var world = new World(new MapCell[3][3]);
        worldRepository.save(world);
        gameEntityRepository.save(new GameEntity(gameId, player, world));
   //     var snap = gameEntityRepository.findByGameId(gameId);.toGame();
//        if(snap.isEmpty()) {
//            gameRepository.create(new Game(gameId, playerName));
//        }
//        else {
//            throw new RuntimeException("Игра с таким gameId уже существует. Измените имя");
//        }
    }

    @Override
    public GameEntity findLastByGameId(String gameId) {
        var snaps = gameEntityRepository.findAllByGameId(gameId);
        if(snaps.isEmpty())
            throw GameNotFoundException(gameId);
        return snaps.getLast();
    }
//
//    public Game findGameSnapshotById(Long id) {
//        //return gameRepository.read(id).orElseThrow(() -> GameNotFoundException(id));
//        return null;
//    }
//
    private RuntimeException GameNotFoundException(String gameId) {
        return new RuntimeException("Game not found, gameId=" + gameId);
    }

    private RuntimeException GameNotFoundException(Long id) {
        return new RuntimeException("Game snapshot with id=" + id + " not found");
    }

    @Override
    public void deleteByGameId(String gameId) {
        var snaps = gameEntityRepository.findAllByGameId(gameId);
        for(var i = 0; i < snaps.size(); i++){
            worldRepository.deleteById(snaps.get(i).getWorld().getId());
            playerRepository.deleteById(snaps.get(i).getPlayer().get);
        }
    }
//
//    public void deleteGameSnapshotById(Long id){
//        //gameRepository.deleteGameSnapshot(id);
//    }
//
    @Override
    public GameEntity updateGameWithPlayerMove(Long id, String direction) {
        return null;
//        var game = findGameSnapshotById(id);
//        var copy = game.deepCopy();
//        var status = copy.tryMovePlayer(Game.Direction.valueOf(direction));
//        if(status){
//            //gameRepository.update(copy);
//            return copy;
//        }
//        return game;
    }

    @PostConstruct
    public void init(){
        System.out.println("Приложение " + appName + ", Версия " + appVersion);
    }
}

