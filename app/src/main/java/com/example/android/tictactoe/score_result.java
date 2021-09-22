package com.example.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class score_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_result);

        Intent intent = getIntent();
        String scoreA = intent.getStringExtra(MainActivity.SCORE_A);
        String scoreB = intent.getStringExtra(MainActivity.SCORE_B);
        String nameA = intent.getStringExtra(MainActivity.NAME_A);
        String nameB = intent.getStringExtra(MainActivity.NAME_B);

        // Capture the layout's TextView and set the string as its text
        TextView player1 = findViewById(R.id.player1);
        TextView player1_score = findViewById(R.id.player1_score);
        TextView player2 = findViewById(R.id.player2);
        TextView player2_score = findViewById(R.id.player2_score);
        TextView winner = findViewById(R.id.winner);
        player1.setText(nameA);
        player1_score.setText(scoreA);
        player2.setText(nameB);
        player2_score.setText(scoreB);
        int A = Integer.parseInt(scoreA);
        int B = Integer.parseInt(scoreB);

        if(A>B){
            winner.setText("Congratulations!  " + nameA);
        }
        else if(B>A){
            winner.setText("Congratulations!  " + nameB);
        }
        else{
            winner.setText("Match Tied");
        }
    }


}