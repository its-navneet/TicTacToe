package com.example.android.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String SCORE_A = "scoreA";
    public static final String SCORE_B = "scoreB";
    public static final String NAME_A = "nameA";
    public static final String NAME_B = "nameB";


    int x = 0;
    int zero = 0;
    String winnerStrX = new String();
    String winnerStr0 = new String();
    boolean gameActive = true;

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // 0 = x
    //1 = 0
    //2 = Null

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};


    int counter = 0;

    public void playerTap(View view) {
        counter++;
        MediaPlayer touch = MediaPlayer.create(this, R.raw.x);
        touch.start();

        TextView turnBox = findViewById(R.id.turn);
        TextView player1 = findViewById(R.id.player1);
        TextView player2 = findViewById(R.id.player2);
        String nameA = player1.getText().toString();
        String nameB = player2.getText().toString();

        turnBox.append(nameA + "'s Turn");

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2) {
            // increase the counter
            // after every tap


            gameState[tappedImage] = activePlayer;


            if (activePlayer == 0) {
                MediaPlayer touchx = MediaPlayer.create(this, R.raw.x);
                touchx.start();
                turnBox.setText(nameB + "'s  Turn ");
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;


            } else {
                MediaPlayer touch0 = MediaPlayer.create(this, R.raw.zero);
                touch0.start();
                turnBox.setText(nameA + "'s  Turn ");
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;

            }
        }

        if (counter == 9) {
            MediaPlayer draw = MediaPlayer.create(this, R.raw.draw);
            draw.start();
            onDraw();
            counter = 0;
        }


        // Check if any player has won
        for (int[] winPosition : winPositions) {

            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                // Somebody has won! - Find out who!
                counter = 0;

                gameActive = false;

                if (gameState[winPosition[0]] == 0) {
                    MediaPlayer win = MediaPlayer.create(this, R.raw.win);
                    win.start();
                    winnerStrX = Integer.toString(++x);
                    winnerStr0 = Integer.toString(zero);
                    winnerAlert(nameA);
                }
                else if (gameState[winPosition[0]] == 1) {
                    MediaPlayer win = MediaPlayer.create(this, R.raw.win);
                    win.start();
                    winnerStr0 = Integer.toString(++zero);
                    winnerStrX = Integer.toString(x);
                    winnerAlert(nameB);
                }

                // Update the status bar for winner announcement
                TextView scoreX = findViewById(R.id.player1_score);
                TextView score0 = findViewById(R.id.player2_score);

                scoreX.setText(winnerStrX);
                score0.setText(winnerStr0);
            }

        }


    }

    void winnerAlert(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(name + " has won !!");

        builder.setMessage("Play again ?");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                boardReset();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                Button result = findViewById(R.id.result);
                showResult(result);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void boardReset(){
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void resetScore(View view) {
        MediaPlayer click = MediaPlayer.create(this,R.raw.click);
        click.start();
        TextView scoreX1 = findViewById(R.id.player1_score);
        TextView score01 = findViewById(R.id.player2_score);
        scoreX1.setText("0");
        score01.setText("0");

    }

    public void showResult(View view) {
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
                    Intent intent = new Intent(MainActivity.this  ,  score_result.class);
                    TextView playerA = findViewById(R.id.player1_score);
                    TextView playerB = findViewById(R.id.player2_score);
                    TextView player1 = findViewById(R.id.player1);
                    TextView player2 = findViewById(R.id.player2);
                    String player1_score = playerA.getText().toString();
                    String player2_score = playerB.getText().toString();

                    String nameA = player1.getText().toString();
                    String nameB = player2.getText().toString();

                    intent.putExtra(SCORE_A, player1_score);
                    intent.putExtra(SCORE_B, player2_score);
                    intent.putExtra(NAME_A, nameA);
                    intent.putExtra(NAME_B, nameB);

                    startActivity(intent);

                }
            }


        };thread.start();

    }


    @SuppressLint("ResourceAsColor")
    public void onDraw()
    {

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Play again ?");

        // Set Alert Title
        builder.setTitle("Match Draw !!");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                boardReset();
            }

        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Button result = findViewById(R.id.result);
                        showResult(result);
                    }
                });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String playerA = intent.getStringExtra(PlayerDetails.EXTRA_MESSAGE_A);
        String playerB = intent.getStringExtra(PlayerDetails.EXTRA_MESSAGE_B);

        TextView player1 = findViewById(R.id.player1);
        TextView player2 = findViewById(R.id.player2);
        player1.setText(playerA);
        player2.setText(playerB);

    }



}
