package com.globalcapsleague.app.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.globalcapsleague.app.activity.LiveGameActivity;
import com.example.globalcapsleagueapp.R;
import com.globalcapsleague.app.views.RotatableView;

public class RebuttalFragment extends Fragment {

    private RotatableView playerCard;
    private RotatableView opponentCard;
    private ConstraintLayout playerCardDummy;
    private ConstraintLayout opponentCardDummy;
    private Context context;
    private LiveGameActivity mainActivity;

    private int opponentRebuttalsStreak;
    private int playerRebuttalsStreak;

    TextView opponentRebuttalsStreakView;
    TextView opponentRebuttalsStreakViewDummy;
    TextView opponentSinks;
    TextView opponentSinksDummy;


    TextView playerRebuttalsStreakView;
    TextView playerRebuttalsStreakViewDummy;
    TextView playerSinks;
    TextView playerSinksDummy;


    public void setMainActivity(LiveGameActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public RebuttalFragment() {
        super(R.layout.fragment_game_rebuttals);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews(view);
        setCardNames(view);
        updateSinks();
        setCardColors();

        opponentCard.setOnClickListener(l -> {

            if (mainActivity.player1Turn) {
                mainActivity.player2Points += 1;
                mainActivity.player1Turn = false;

                playerRebuttalsStreakViewDummy.setText(String.valueOf(playerRebuttalsStreak));
                playerSinksDummy.setText(String.valueOf(mainActivity.player1Sinks));

                setCardColors();
                playerCard.animateReverse(() -> {
                    mainActivity.hideRebuttalsScreen();
                    if(mainActivity.player2Points==11){
                        mainActivity.setFragment(WinnerFragment.class,android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                });
                return;
            }
            mainActivity.player2Sinks += 1;
            mainActivity.player2Rebuttals += 1;
            opponentRebuttalsStreak += 1;
            mainActivity.player1Turn = true;

            opponentRebuttalsStreakView.setText(String.valueOf(opponentRebuttalsStreak));
            opponentRebuttalsStreakViewDummy.setText(String.valueOf(opponentRebuttalsStreak + 1));
            playerRebuttalsStreakViewDummy.setText(String.valueOf(playerRebuttalsStreak));

            opponentSinks.setText(String.valueOf(mainActivity.player2Sinks));
            opponentSinksDummy.setText(String.valueOf(mainActivity.player2Sinks + 1));
            playerSinksDummy.setText(String.valueOf(mainActivity.player1Sinks));
            setCardColors();

            playerCard.animateReverse(() -> {
                playerSinksDummy.setText(String.valueOf(mainActivity.player1Sinks + 1));
                playerRebuttalsStreakViewDummy.setText(String.valueOf(playerRebuttalsStreak + 1));
//                mainActivity.displayRebuttalsScreen();
            });
        });

        playerCard.setOnClickListener(l -> {

            if (!mainActivity.player1Turn) {
                mainActivity.player1Points += 1;
                mainActivity.player1Turn = true;

                opponentRebuttalsStreakViewDummy.setText(String.valueOf(opponentRebuttalsStreak));
                opponentSinksDummy.setText(String.valueOf(mainActivity.player2Sinks));

                setCardColors();
                opponentCard.animateReverse(() -> {
                    mainActivity.hideRebuttalsScreen();
                    if(mainActivity.player1Points==11){
                        mainActivity.setFragment(WinnerFragment.class,android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                });
                return;
            }
            mainActivity.player1Sinks += 1;
            mainActivity.player1Rebuttals += 1;
            playerRebuttalsStreak += 1;
            mainActivity.player1Turn = false;

            playerRebuttalsStreakView.setText(String.valueOf(playerRebuttalsStreak));
            playerRebuttalsStreakViewDummy.setText(String.valueOf(playerRebuttalsStreak + 1));
            opponentRebuttalsStreakViewDummy.setText(String.valueOf(opponentRebuttalsStreak));

            playerSinks.setText(String.valueOf(mainActivity.player1Sinks));
            playerSinksDummy.setText(String.valueOf(mainActivity.player1Sinks + 1));
            opponentSinksDummy.setText(String.valueOf(mainActivity.player2Sinks));
            setCardColors();

            opponentCard.animateReverse(() -> {
                opponentSinksDummy.setText(String.valueOf(mainActivity.player2Sinks + 1));
                opponentRebuttalsStreakViewDummy.setText(String.valueOf(opponentRebuttalsStreak + 1));
//                mainActivity.displayRebuttalsScreen();
            });

        });
    }

    private void initializeViews(View view) {
        opponentCard = view.findViewById(R.id.bottom_card);
        playerCard = view.findViewById(R.id.top_card);
        opponentCardDummy = view.findViewById(R.id.bottom_card_dummy);
        opponentCard.setCameraDistance(15000 * context.getResources().getDisplayMetrics().density);

        playerCardDummy = view.findViewById(R.id.top_card_dummy);
        playerCard.setCameraDistance(15000 * context.getResources().getDisplayMetrics().density);

        opponentRebuttalsStreakView = opponentCard.findViewById(R.id.rebuttals_streak);
        opponentRebuttalsStreakViewDummy = opponentCardDummy.findViewById(R.id.rebuttals_streak);
        opponentSinks = opponentCard.findViewById(R.id.details_sinks);
        opponentSinksDummy = opponentCardDummy.findViewById(R.id.details_sinks);

        playerRebuttalsStreakView = playerCard.findViewById(R.id.rebuttals_streak);
        playerRebuttalsStreakViewDummy = playerCardDummy.findViewById(R.id.rebuttals_streak);
        playerSinks = playerCard.findViewById(R.id.details_sinks);
        playerSinksDummy = playerCardDummy.findViewById(R.id.details_sinks);

    }

    public void updateSinks() {
        updateText(playerSinks, mainActivity.player1Sinks);
        updateText(playerSinksDummy, mainActivity.player1Sinks + 1);
        updateText(opponentSinks, mainActivity.player2Sinks);
        updateText(opponentSinksDummy, mainActivity.player2Sinks + 1);
        updateText(opponentRebuttalsStreakViewDummy, 1);
        updateText(playerRebuttalsStreakViewDummy, 1);

    }

    private void updateText(TextView text, int value) {
        text.setText(String.valueOf(value));
    }

    private void setCardNames(View view) {
        ((TextView) playerCard.findViewById(R.id.details_title)).setText("You");
        ((TextView) view.findViewById(R.id.top_card_dummy).findViewById(R.id.details_title)).setText("You");
        ((TextView) opponentCard.findViewById(R.id.details_title)).setText("Opponent");
        ((TextView) view.findViewById(R.id.bottom_card_dummy).findViewById(R.id.details_title)).setText("Opponent");

    }

    private void setCardColors() {
        if (mainActivity.player1Turn) {
            playerCard.setBackgroundResource(R.drawable.round_top_corners_green);
            opponentCard.setBackgroundResource( R.drawable.round_bottom_corners);
            playerCardDummy.setBackgroundResource(R.drawable.round_top_corners);
            opponentCardDummy.setBackgroundResource(R.drawable.round_bottom_corners_green);

            opponentRebuttalsStreakViewDummy.setText(String.valueOf(opponentRebuttalsStreak));
            opponentSinksDummy.setText(String.valueOf(mainActivity.player2Sinks));

        } else {
            playerCard.setBackgroundResource(R.drawable.round_top_corners);
            opponentCard.setBackgroundResource( R.drawable.round_bottom_corners_green);
            playerCardDummy.setBackgroundResource(R.drawable.round_top_corners_green);
            opponentCardDummy.setBackgroundResource( R.drawable.round_bottom_corners);

            playerRebuttalsStreakViewDummy.setText(String.valueOf(playerRebuttalsStreak));
            playerSinksDummy.setText(String.valueOf(mainActivity.player1Sinks));
        }
    }
}
