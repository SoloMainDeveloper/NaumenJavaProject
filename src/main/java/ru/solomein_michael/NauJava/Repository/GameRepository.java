package ru.solomein_michael.NauJava.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.solomein_michael.NauJava.Game.Game;

import java.util.List;
import java.util.Objects;

@Component
public class GameRepository implements CrudRepository<Game, Long> {
    private final List<Game> games;

    @Autowired
    public GameRepository(List<Game> games){
        this.games = games;
    }

    @Override
    public void create(Game game) {
        games.add(game);
    }

    @Override
    public Game read(Long id) {
        for(var game : games){
            if(Objects.equals(game.getId(), id))
                return game;
        }
        return null;
    }

    @Override
    public void update(Game updatedGame) {
        var id = updatedGame.getId();
        for(var game : games){
            if(Objects.equals(game.getId(), id))
                game = updatedGame;
        }
    }

    @Override
    public void delete(Long id) {
        for(var game : games){
            if(Objects.equals(game.getId(), id))
                games.remove(game);
        }
    }
}
