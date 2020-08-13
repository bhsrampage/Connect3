package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
   boolean gameactive= true;
    int activeplayer =0; //0: golden ; 1: silver; 2:empty
    int gamestates[]= {2,2,2,2,2,2,2,2,2};
    int win[][]= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view){
        ImageView counter = (ImageView) view;
        Log.i("Tag",counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
       if(gamestates[tappedcounter]==2 && gameactive){
        gamestates[tappedcounter]= activeplayer;
        counter.setTranslationY(-1500);
        if(activeplayer==1){
        counter.setImageResource(R.drawable.token2); activeplayer=0;}
        else {
            counter.setImageResource(R.drawable.token1); activeplayer=1;
        }
        counter.animate().translationYBy(1500).setDuration(300);
        String coin ="";
        for(int[] winningposition : win) {
            if (gamestates[winningposition[0]] == gamestates[winningposition[1]] && gamestates[winningposition[1]] == gamestates[winningposition[2]] &&
                    gamestates[winningposition[0]] != 2) //someone has won
            {
                gameactive = false;
                if (activeplayer == 0) coin = "Silver";
                else coin = "Golden";

                Toast.makeText(this, coin + " coin has won", Toast.LENGTH_SHORT).show();
                Button playagain = (Button) findViewById(R.id.playagain);
                TextView wintextView = (TextView) findViewById(R.id.wintextView);
                playagain.setVisibility(view.VISIBLE);
                wintextView.setVisibility(view.VISIBLE);
                wintextView.setText(coin + " coin has won");
            }
        }
       }
    }
    public void PlayAgain(View view){


        Button playagain = (Button) findViewById(R.id.playagain);
        TextView wintextView = (TextView) findViewById(R.id.wintextView);
        playagain.setVisibility(view.INVISIBLE);
        wintextView.setVisibility(view.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        Arrays.fill(gamestates, 2);

        gameactive= true;
        activeplayer = 0; //0: golden ; 1: silver; 2:empty

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
