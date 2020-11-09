package com.game.tictactoe.model;

public class Player {
    // Player name
    private String name;

    // Player value (x / 0)
    private String value;

    // initializing the player by name and value
    public Player(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
