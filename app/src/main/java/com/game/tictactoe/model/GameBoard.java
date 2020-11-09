package com.game.tictactoe.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import static com.game.tictactoe.utility.StringUtility.isNullOrEmpty;

// Board for playing the game
public class GameBoard {
    private static final String TAG = GameBoard.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    // 1st Player with X value by default
    public Player player1;

    // 2nd Player with O value
    public Player player2;

    // It's a case when no player will be win the game and game is draw
    public Player gameDraw = new Player("Game is Draw", "-");

    // It's a current player who is going to play his innings
    public Player currentPlayer;

    // It holds x and y position of game box
    public Cell[][] cells;

    // Mutable live data list of player type to observe the winner
    public MutableLiveData<Player> winner = new MutableLiveData<>();

    // Initializing the cell of board and players value
    public GameBoard(String playerOne, String playerTwo) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "x");
        player2 = new Player(playerTwo, "o");
        currentPlayer = player1;
    }

    // On completion of game checking if any one winner then set name of player as a winner else set game is draw
    public boolean hasGameEnded() {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
            winner.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winner.setValue(gameDraw);
            return true;
        }

        return false;
    }
    // For Checking horizontal cell completion
    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    // For Checking vertical cell completion
    public boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    // For Checking Diagonals cell completion
    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }
// checking Game board is full or not
    public boolean isBoardFull() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                if (cell == null || cell.isEmpty())
                    return false;
        return true;
    }

    /**
     * 2 cells are equal if:
     * - Both are none null
     * - Both have non null values
     * - both have equal values
     *
     * @param cells: Cells to check if are equal
     * @return
     */
    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Cell cell : cells)
            if (cell == null || isNullOrEmpty(cell.getPlayer().getValue()))
                return false;

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++)
            if (!comparisonBase.getPlayer().getValue().equals(cells[i].getPlayer().getValue()))
                return false;

        return true;
    }

    /* change the player (if existing player is player1 then it will be change to player2
      else it will be change player2 to player1.
       so that each player can play their inning once a time*/
    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    // set value of player and cell to null
    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }

}
