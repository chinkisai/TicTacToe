package com.game.tictactoe.model;

import com.game.tictactoe.utility.StringUtility;

public class Cell {

    private Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isEmpty(){
        return player==null || StringUtility.isNullOrEmpty(player.getValue());
    }
}
