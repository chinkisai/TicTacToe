package com.game.tictactoe.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.game.tictactoe.R;

public class GameCompleteDialog extends DialogFragment {
    private View rootView;

    // To hold the context of MainActivity
    private MainActivity activity;

    //  To hold the Name of player who won the game and Game Draw in case of no winner
    private String winnerName;

    // Initialize the value of context and name of winner
    public static GameCompleteDialog newInstance(MainActivity activity, String winnerName) {
        GameCompleteDialog dialog = new GameCompleteDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    // Showing the result of game (who is winner) and providing option to play again
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.play_again, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    // set the the name of winner and change the background color of textview in case of Game is Draw
    private void initViews() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.game_complete_dialog, null, false);
        TextView tvWinnerName = (TextView) rootView.findViewById(R.id.tv_winner);
        tvWinnerName.setText(winnerName);
        if(winnerName.equalsIgnoreCase("Game is Draw")) {
            ((TextView) rootView.findViewById(R.id.tv_Title)).setVisibility(View.GONE);
            tvWinnerName.setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    // To dismiss dialog and restart game
    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }
}
