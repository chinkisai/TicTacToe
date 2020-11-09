package com.game.tictactoe.view;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.game.tictactoe.R;
import com.game.tictactoe.databinding.ActivityMainBinding;
import com.game.tictactoe.model.Player;
import com.game.tictactoe.viewmodel.GameViewModel;

import static com.game.tictactoe.utility.StringUtility.isNullOrEmpty;

public class MainActivity extends AppCompatActivity {
    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";
    private static final String NO_WINNER = "Game is Draw";
    private GameViewModel gameViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        promptForPlayers();
    }

    // To start the dialog to take the name of player and start the game
    public void promptForPlayers() {
        GameStartDialog dialog = GameStartDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    // set the name of players
    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    // Binding view with ViewModel
    private void initDataBinding(String player1, String player2) {
        ActivityMainBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // creating instance of ViewModel using ViewModelProvider
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.init(player1, player2);
        // set game view model in activity
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    // observes the winner LiveData instance.the winner’s value is changed which triggers the onGameWinnerChanged method after completion of the game
    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    // To provide game winner and show dialog of completion of game
    @VisibleForTesting
    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.getName()) ? NO_WINNER : winner.getName();
        GameCompleteDialog dialog = GameCompleteDialog.newInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }


}