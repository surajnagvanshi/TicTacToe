package com.example.helloworld.tictactoe;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

//                    Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();


public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    int playerTurn;
    boolean isWaiting=true;
    char aChar[]=new  char[9];
    public void columnClicked(View view){

        //ImageView imageView=findViewById(view.getId());


        ImageView imageView=(ImageView) view;
        String resourceIdAsString = view.getResources().getResourceName(view.getId());
        int position=Integer.parseInt(Character.toString(resourceIdAsString.charAt(resourceIdAsString.length()-1)))-1;
        if(aChar[position]!='o'&&aChar[position]!='x'&&!isWaiting) {
            if (playerTurn == 1) {

                isWaiting=true;
                imageView.animate().rotationBy(1260f).setDuration(500).start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms

                        isWaiting=false;
                    }
                }, 500);
                imageView.setImageResource(R.drawable.cross);
                playerTurn--;
                aChar[position] = 'x';
                checkWinConditions(1);
            } else {
                imageView.setImageResource(R.drawable.circle);
                playerTurn++;
                aChar[position] = 'o';
                checkWinConditions(2);
            }
        }
    }

    private void checkWinConditions(int player) {

        if(aChar[0]==aChar[1]&&aChar[1]==aChar[2]||aChar[3]==aChar[4]&&aChar[4]==aChar[5]||aChar[6]==aChar[7]&&aChar[7]==aChar[8]
                ||aChar[0]==aChar[3]&&aChar[3]==aChar[6]||aChar[1]==aChar[4]&&aChar[4]==aChar[7]||aChar[2]==aChar[5]&&aChar[5]==aChar[8]
                ||aChar[0]==aChar[4]&&aChar[4]==aChar[8]||aChar[2]==aChar[4]&&aChar[4]==aChar[6]){
            Toast.makeText(this,"player "+player+" won the match", Toast.LENGTH_LONG).show();

            isWaiting=true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms

                    restart();
                }
            }, 1000);



        }

    }

    public void reset(View view){
        restart();
    }

    private void restart(){
        int images[]=new int[]{R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9};
        for(int temp=0;temp<9;temp++) {
            aChar[temp] = (char) temp;
            ImageView imageView=findViewById(images[temp]);
            imageView.setImageResource(R.drawable.ic_launcher_background);
            playerTurn=1;
            isWaiting=false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restart();
    }
}
