package ru.solomein_michael.NauJava.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.solomein_michael.NauJava.Game.Game;

import java.util.*;

@Component
public class GameRepository implements CrudRepository<Game, String> {
    private final List<Game> games;

    @Autowired
    public GameRepository(List<Game> games){
        this.games = games;
    }

    @Override
    public void create(Game game) {
        game.setId((long)games.size());
        games.add(game);
    }

    @Override
    public Optional<Game> read(String gameId) {
        ArrayList<Game> snapshotsOfGame = new ArrayList<>();
        for(var i = 0; i < games.size(); i++){
            if(Objects.equals(games.get(i).getGameId(), gameId))
                snapshotsOfGame.add(games.get(i));
        }
        return snapshotsOfGame.stream().max(Comparator.comparing(Game::getId));
    }

    public Optional<Game> read(Long id) {
        for(var i = 0; i < games.size(); i++){
            if(Objects.equals(games.get(i).getId(), id))
                return Optional.of(games.get(i));
        }
        return Optional.empty();
    }

    @Override
    public void update(Game updatedGame) {
        create(updatedGame);
//        var id = updatedGame.getId();
//        for(var i = 0; i < games.size(); i++){
//            if(Objects.equals(games.get(i).getId(), id))
//                games.set(i, updatedGame);
//        }
    }

    @Override
    public void deleteByGameId(String gameId) {
        var count = 0;
        for(var game: games){
            if(Objects.equals(game.getGameId(), gameId)){
                games.remove(game);
                count++;
            }
        }
        if(count == 0)
            throw new RuntimeException("Game с gameId=" + gameId + " не была удалена");
    }

    public void deleteGameSnapshot(Long id){
        for(var i = 0; i < games.size(); i++){
            if(Objects.equals(games.get(i).getId(), id)){
                games.remove(i);
                return;
            }
        }
        throw new RuntimeException("Game Snapshot с id=" + id + " не был удален");
    }
}
