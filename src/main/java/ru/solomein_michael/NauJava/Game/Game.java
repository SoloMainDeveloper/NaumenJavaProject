package ru.solomein_michael.NauJava.Game;

public class Game {
    public enum Direction {
        Right ("right"),
        Left ("left"),
        Down ("down"),
        Up ("up");

        private String dir;

        Direction(String dir){
            this.dir = dir;
        }
    }

    private final Long id;
    private final Player player;
    private MapCell[][] map;

    public Game(Long id, String playerName){
        this.id = id;
        player =  new Player(playerName, 0, 0);
        map = new MapCell[2][2];
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer(){
        return player;
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
        if(currentY + y >= 0 && currentY + y <= map.length - 1 && currentX + x >= 0 && currentX + x <= map[currentY].length - 1){
            player.updatePos(currentX + x, currentY + y);
            return true;
        }
        return false;
    }
}
