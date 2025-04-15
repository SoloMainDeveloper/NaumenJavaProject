package ru.solomein_michael.NauJava.entity;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
