package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class play extends AppCompatActivity {
    private long n,o1,o2,o3;
    private int i;
    private long[] arr;
    private static int qn_no=1,scores=0,i_prev1=0,i_prev2=0;
    private Intent intent;
    public static final String EXTRA_MESSAGE = "com.example.thefactorfactor.CHECK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        intent = getIntent();
        arr=new long[6];
        n = intent.getLongExtra(NewGame.EXTRA_MESSAGE, -1);
        TextView textView = (TextView) findViewById(R.id.textView_qn);
        String str=Integer.toString(qn_no)+")";
        textView.setText(str);
        textView = (TextView) findViewById(R.id.textView13);
        textView.setText(Integer.toString(scores));
        qn_no++;
        textView=(TextView)findViewById(R.id.textView6);
        textView.setText(Long.toString(n));
        Random rand = new Random();
        int  wrg = 0;
        o1 = rand.nextInt((int)n) + 1;
        o2 = 0;
        o3 = 0;
        if (n % o1 == 0)
            i = 1;
        else
            wrg++;
        while (true&&n!=1&&n!=2) {
            long t = rand.nextInt((int)n) + 1;
            if (t != o1 && t != o2) {
                if (n % t == 0) {
                    if (i == 0) {
                        if (o2 == 0) {
                            o2 = t;
                            i = 2;
                        }
                        else {
                            o3 = t;
                            i=3;
                            break;
                        }
                    }
                }
                else if (wrg != 2) {
                    if (o2 == 0)
                        o2 = t;
                    else {
                        o3 = t;
                        break;
                    }
                    wrg++;
                }
            }
        }
        if(i_prev1==i||i_prev2==i){
            long t;
            if(i_prev1!=1&&i_prev2!=1) {
                switch (i) {
                    case 2:
                        t = o2;
                        o2 = o1;
                        o1 = t;
                        break;
                    case 3:
                        t = o3;
                        o3 = o1;
                        o1 = t;
                }
                i=1;
            }
            else if(i_prev1!=2&&i_prev2!=2) {
                switch (i) {
                    case 1:
                        t = o1;
                        o1 = o2;
                        o2 = t;
                        break;
                    case 3:
                        t = o3;
                        o3 = o2;
                        o2 = t;
                }
                i = 2;
            }
            else {
                switch (i) {
                    case 1:
                        t = o1;
                        o1 = o3;
                        o3 = t;
                        break;
                    case 2:
                        t = o2;
                        o2 = o3;
                        o3 = t;
                }
                i = 3;
            }
        }
        i_prev2=i_prev1;
        i_prev1=i;
        Button btn = (Button) findViewById(R.id.button_o1);
        btn.setText(Long.toString(o1));
        btn = (Button) findViewById(R.id.button_o2);
        btn.setText(Long.toString(o2));
        btn = (Button) findViewById(R.id.button_o3);
        btn.setText(Long.toString(o3));

    }
    public void button1(View view)
    {
        arr[4]=1;
        if(n%o1==0)
            scores++;
        intent = new Intent(this, Answer.class);
        displayAnswer();
    }

    public void button2(View view)
    {
        arr[4]=2;
        if(n%o2==0)
            scores++;
        intent = new Intent(this, Answer.class);
        displayAnswer();
    }

    public void button3(View view)
    {
        arr[4]=3;
        if(n%o3==0)
            scores++;
        intent = new Intent(this, Answer.class);
        displayAnswer();
    }
    public void displayAnswer() {

        arr[0]=n;
        arr[1]=o1;
        arr[2]=o2;
        arr[3]=o3;
        arr[5]=i;
        intent.putExtra(EXTRA_MESSAGE,arr);
        startActivity(intent);
    }
}
