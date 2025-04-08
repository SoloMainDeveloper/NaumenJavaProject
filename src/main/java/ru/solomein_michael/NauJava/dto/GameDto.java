package ru.solomein_michael.NauJava.dto;

import ru.solomein_michael.NauJava.entity.Game;
import ru.solomein_michael.NauJava.entity.MapCell;

public class GameDto {
    public String gameId;
    public String playerName;
    public int playerPosX;
    public int playerPosY;
    public MapCell[][] map;

    public GameDto(Game game){
        var copy = game.deepCopy();
        this.gameId = copy.getGameId();
        var player = copy.getPlayer();
        this.playerName = player.getName();
        this.playerPosX = player.getPosX();
        this.playerPosY = player.getPosY();
        this.map = copy.getWorld().getMap();
    }
}
