package ru.solomein_michael.NauJava.Game;

public class Player {
    private final String name;
    private int posX;
    private int posY;

    public Player(String name, int posX, int posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    public Player deepCopy(){
        return new Player(name, posX, posY);
    }

    public String getName() {
        return name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void updatePos(int x, int y){
        posX = x;
        posY = y;
    }
}
