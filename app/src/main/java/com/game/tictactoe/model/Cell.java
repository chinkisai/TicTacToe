package com.game.tictactoe.model;

import com.game.tictactoe.utility.StringUtility;

public class Cell {

    // player of game
    private Player player;

    // for storing current player at selected position
    public Cell(Player player) {
        this.player = player;
    }

    // to get the player
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // it will return true if value of player is null, undefine or empty
    public boolean isEmpty(){
        return player==null || StringUtility.isNullOrEmpty(player.getValue());
    }
}
