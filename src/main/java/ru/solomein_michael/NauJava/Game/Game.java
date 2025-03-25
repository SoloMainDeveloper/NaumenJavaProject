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

    private Long id;
    private final String gameId;
    private Player player;
    private MapCell[][] map;

    public Game(String gameId, String playerName){
        this.gameId = gameId;
        player = new Player(playerName, 0, 0);
        map = new MapCell[2][2];
    }

    public Game(String gameId, Player player, MapCell[][] map){
        this.gameId = gameId;
        this.player = player;
        this.map = map;
    }

    public Game deepCopy(){
        var copiedPlayer = this.player.deepCopy();
        var copiedMap = new MapCell[map.length][];
        for (int i = 0; i < map.length; i++) {
            copiedMap[i] = new MapCell[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                copiedMap[i][j] = map[i][j] != null ? map[i][j].deepCopy() : null;
            }
        }
        return new Game(gameId, copiedPlayer, copiedMap);
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getGameId(){
        return gameId;
    }

    public Player getPlayer(){
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    public MapCell[][] getMap(){
        return map;
    }

    private void setMap(MapCell[][] map){
        this.map = map;
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
