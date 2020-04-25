package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class FinalScoresActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT1 = 6500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_scores);
        Intent intent = getIntent();
        long[] final_data=intent.getLongArrayExtra(Answer.EXTRA_MESSAGE);
        TextView textView = (TextView)findViewById(R.id.textView17);
        textView.setText(Long.toString(final_data[0]));
        textView = (TextView)findViewById(R.id.textView18);
        textView.setText(Long.toString(final_data[1]));
        textView = (TextView)findViewById(R.id.textView19);
        textView.setText(Long.toString(final_data[2]));
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent=new Intent(FinalScoresActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT1);
    }
}
