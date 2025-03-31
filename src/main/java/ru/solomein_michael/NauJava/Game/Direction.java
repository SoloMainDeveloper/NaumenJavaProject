package ru.solomein_michael.NauJava.Game;

public enum Direction {
    Right("right"),
    Left("left"),
    Down("down"),
    Up("up");

    private String dir;

    Direction(String dir) {
        this.dir = dir;
    }
}