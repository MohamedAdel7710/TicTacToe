package com.example.android.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0:x,1:o
    int playerCounter=0;
    //position used or not
    int [] posPlayed={2,2,2,2,2,2,2,2,2};
    //winning positions
    int [][] winPositions={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    //onGame
    boolean onGame=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dropIn(View view) {
        ImageView position = (ImageView) view;
        int clickedPos = Integer.parseInt(position.getTag().toString());
        if (posPlayed[clickedPos] == 2&&onGame) {
            posPlayed[clickedPos] =playerCounter;
            if (playerCounter == 0) {
                position.setImageResource(R.drawable.x_mark_white);

                playerCounter = 1;
            } else {
                position.setImageResource(R.drawable.o_mark_white);

                playerCounter = 0;
            }

            position.animate().alpha(1).setDuration(2000);
            for(int [] winPos:winPositions){
                if(posPlayed[winPos[0]]!=2
                        &&posPlayed[winPos[0]]==posPlayed[winPos[1]]
                        &&posPlayed[winPos[1]]==posPlayed[winPos[2]])
                {
                    onGame=false;
                    String winner ="X";
                    if(posPlayed[winPos[1]]==1){
                        winner="O";
                    }
                    TextView text =(TextView)findViewById(R.id.textView);
                    text.setText(winner+" has won");
                    LinearLayout result=(LinearLayout)findViewById(R.id.result_layout);
                    result.setVisibility(View.VISIBLE);
                }
                else{
                    boolean isOver=true;
                    for(int i=0;i<posPlayed.length;i++){
                        if(posPlayed[i]==2){
                            isOver=false;
                        }
                    }
                    if(isOver){
                        TextView text =(TextView)findViewById(R.id.textView);
                        text.setText("It is a draw");
                        LinearLayout result=(LinearLayout)findViewById(R.id.result_layout);
                        result.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }
    public void playAgain(View view){
        onGame=true;

        LinearLayout result=(LinearLayout)findViewById(R.id.result_layout);

        result.setVisibility(View.INVISIBLE);

        playerCounter=0;

        for(int i=0;i<posPlayed.length;i++){
            posPlayed[i]=2;

        }
        //ImageView imageView=(ImageView)findViewById(R.id.imageView);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gameBLank);
        for(int i=0;i<gridLayout.getChildCount();i++) {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
                   //imageView.setImageResource(0);

    }
}
