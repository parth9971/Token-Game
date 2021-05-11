package com.example.tokengame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int player = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningposition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameactive = true;

    public void click(View view) {


        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = player;
            if (player == 0) {
                player = 1;
                counter.setTranslationY(-1500);
                counter.setImageResource(R.drawable.b2);
                counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            } else {
                player = 0;
                counter.setTranslationY(-1500);
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            }
            for (int[] winningpositions : winningposition) {
                if (gamestate[winningpositions[0]] == gamestate[winningpositions[1]] && gamestate[winningpositions[1]] == gamestate[winningpositions[2]] && gamestate[winningpositions[0]] != 2) {
                    gameactive = false;
                    String winner;
                    if (player == 1) {
                        winner = "blue";
                    } else {
                        winner = "red";
                    }


                    Button button = findViewById((R.id.playagain));
                    TextView win = findViewById(R.id.winner);
                    win.setText(winner + " has won!");
                    button.setVisibility(View.VISIBLE);
                    win.setVisibility(View.VISIBLE);

                }
            }
        }

    }


    public void one(View view) {
        Button button = (Button)findViewById((R.id.playagain));

        TextView win = (TextView)findViewById(R.id.winner);
        button.setVisibility(View.INVISIBLE);
        win.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridlayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }


      for (int j=0;j<gamestate.length;j++){
          gamestate[j]=2;
      }
       gameactive = true;
        player = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}