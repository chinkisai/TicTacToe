package com.game.tictactoe.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.game.tictactoe.R;

public class GameCompleteDialog extends DialogFragment {
    private View rootView;
    private MainActivity activity;
    private String winnerName;

    public static GameCompleteDialog newInstance(MainActivity activity, String winnerName) {
        GameCompleteDialog dialog = new GameCompleteDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }
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

    private void initViews() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.game_complete_dialog, null, false);
        TextView tvWinnerName = (TextView) rootView.findViewById(R.id.tv_winner);
        tvWinnerName.setText(winnerName);
        if(winnerName.equalsIgnoreCase("Game is Draw")) {
            ((TextView) rootView.findViewById(R.id.tv_Title)).setVisibility(View.GONE);
            tvWinnerName.setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }
}
