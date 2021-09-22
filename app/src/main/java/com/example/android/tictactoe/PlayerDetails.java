package com.example.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerDetails extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_A = "player1";
    public static final String EXTRA_MESSAGE_B = "player2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);


    }

    public void play(View view){
        MediaPlayer click = MediaPlayer.create(this,R.raw.click);
        click.start();
        Thread thread =new Thread(){
            public void run(){
                try{
                    sleep(200);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(PlayerDetails.this  ,  MainActivity.class);
                    EditText playerA = (EditText) findViewById(R.id.player_name1);
                    EditText playerB = (EditText) findViewById(R.id.player_name2);
                    String player1 = playerA.getText().toString();
                    String player2 = playerB.getText().toString();
                    intent.putExtra(EXTRA_MESSAGE_A, player1);
                    intent.putExtra(EXTRA_MESSAGE_B, player2);
                    startActivity(intent);

                }
            }


        };thread.start();

    }



}