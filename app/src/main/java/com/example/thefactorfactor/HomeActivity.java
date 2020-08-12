package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }
    public void longestStreak(View view){
        Intent intent = new Intent(this, LongestWinningStreak.class);
        startActivity(intent);
    }
}
