package ru.solomein_michael.NauJava.Game;

import com.google.gson.Gson;
import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String gameId;

    @OneToOne
    Player player;

    @OneToOne
    World world;

    public GameEntity(){
    }

    public GameEntity(String gameId, Player player, World world){
        this.gameId = gameId;
        this.player = player;
        this.world = world;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
