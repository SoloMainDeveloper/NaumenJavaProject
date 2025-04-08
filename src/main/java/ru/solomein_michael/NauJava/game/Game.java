package ru.solomein_michael.NauJava.game;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String gameId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Player player;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    World world;

    public Game(){
    }

    public Game(String gameId, Player player, World world){
        this.gameId = gameId;
        this.player = player;
        this.world = world;

    }

    public Game deepCopy(){
        var copiedPlayer = this.player.deepCopy();
        var map = world.getMap();
        var copiedMap = new MapCell[map.length][];
        for (int i = 0; i < map.length; i++) {
            copiedMap[i] = new MapCell[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                copiedMap[i][j] = map[i][j] != null ? map[i][j].deepCopy() : null;
            }
        }
        return new Game(gameId, copiedPlayer, new World(copiedMap));
    }

    public boolean tryMovePlayer(Direction dir){
        return switch(dir){
            case Down -> tryMovePlayer(0, 1);
            case Up -> tryMovePlayer(0, -1);
            case Left -> tryMovePlayer(-1, 0);
            case Right -> tryMovePlayer(1, 0);
        };
    }

    private boolean tryMovePlayer(int x, int y){
        var currentY = player.getPosY();
        var currentX = player.getPosX();
        var map = world.getMap();
        if(currentY + y >= 0 && currentY + y <= map.length - 1 && currentX + x >= 0 && currentX + x <= map[currentY].length - 1){
            player.updatePos(currentX + x, currentY + y);
            return true;
        }
        return false;
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
