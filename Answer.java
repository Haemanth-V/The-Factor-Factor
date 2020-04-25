package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Answer extends AppCompatActivity {
    private static long score=0;
    private static int streak=0,qns=1,max=0;
    public static final String EXTRA_MESSAGE = "com.example.thefactorfactor.SCORES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent intent=getIntent();
        long[] array= intent.getLongArrayExtra(play.EXTRA_MESSAGE);
        TextView textView=(TextView)findViewById(R.id.textView11);
        textView.setText(Long.toString(array[0]));
        textView=(TextView)findViewById(R.id.textView_qn_num);
        String p=Integer.toString(qns)+")";
        textView.setText(p);
        textView=(TextView)findViewById(R.id.textView_o1);
        textView.setText(Long.toString(array[1]));
        textView=(TextView)findViewById(R.id.textView_o2);
        textView.setText(Long.toString(array[2]));
        textView=(TextView)findViewById(R.id.textView_o3);
        textView.setText(Long.toString(array[3]));
        RelativeLayout colour = (RelativeLayout)findViewById(R.id.Final);
        if (array[4]==array[5]) {
            colour.setBackgroundColor(Color.GREEN);
            score++;
            streak++;
        }
        else {
            colour.setBackgroundColor(Color.RED);
            if (array[5] == 1)
                textView = (TextView) findViewById(R.id.textView_o1);
            else if (array[5] == 2)
                textView = (TextView) findViewById(R.id.textView_o2);
            else if (array[5] == 3)
                textView = (TextView) findViewById(R.id.textView_o3);
            textView.setBackgroundColor(Color.GREEN);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            if(max<streak)
                max=streak;
            streak=0;
        }
        textView=(TextView)findViewById(R.id.textView12);
        textView.setText(Long.toString(score));
        qns++;
    }
    public void continueGame(View view){
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }
    public void quitGame(View view){
        Intent intent = new Intent(this, FinalScoresActivity.class);
        long[] imp= new long[3];
        imp[0]=qns-1;
        imp[1]=score;
        imp[2]=max;
        intent.putExtra(EXTRA_MESSAGE, imp);
        startActivity(intent);
    }
}
