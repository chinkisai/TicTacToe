package com.game.tictactoe.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.game.tictactoe.model.Cell;
import com.game.tictactoe.model.GameBoard;
import com.game.tictactoe.model.Player;

import static com.game.tictactoe.utility.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {
    public ObservableArrayMap<String, String> cells;
    private GameBoard gameBoard;

    public void init(String player1, String player2) {
        gameBoard = new GameBoard(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    // Putting value (X/0) at cell position of game board and if game is not finished then switching the player (from player1 to player2 or player2 to player1)
    public void onClickedCellAt(int row, int column) {
        if (gameBoard.cells[row][column] == null) {
            gameBoard.cells[row][column] = new Cell(gameBoard.currentPlayer);
            cells.put(stringFromNumbers(row, column), gameBoard.currentPlayer.getValue());
            if (gameBoard.hasGameEnded())
                gameBoard.reset();
            else
                gameBoard.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return gameBoard.winner;
    }
}
