package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewGame extends AppCompatActivity {
    EditText number;
    private int status;
    public static final String EXTRA_MESSAGE = "com.example.thefactorfactor.NUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        Intent intent = getIntent();
        status = intent.getIntExtra("STATUS",0);
    }
    public void sendGame(View view) {
        number=(EditText) findViewById(R.id.editText2);
        long num = Long.parseLong(number.getText().toString());
        if(num<1) {
            String s = "Invalid! Enter again.";
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, play.class);
            intent.putExtra("STATUS",status);
            intent.putExtra(EXTRA_MESSAGE,num);
            startActivity(intent);
            finish();
        }
    }

}
