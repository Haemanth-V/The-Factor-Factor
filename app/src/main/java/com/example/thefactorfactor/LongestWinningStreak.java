package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class LongestWinningStreak extends AppCompatActivity {

    private String winningStreak;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longest_winning_streak);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE);
        winningStreak = "Longest Winning Streak : "+sharedPreferences.getString(MainActivity.STREAK,"0");
        textView = (TextView)findViewById(R.id.textViewStreak);
        textView.setText(winningStreak);
//        textView.setTextColor(Integer.parseInt("#E65100"));
    }
}