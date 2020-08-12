package com.example.thefactorfactor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class play extends AppCompatActivity {
    private long n,o1,o2,o3;
    private int i;
    private long[] arr;
    private static int qn_no=1,scores=0,i_prev1=0,i_prev2=0;
    private long timeLeft=10000;
    private CountDownTimer countDownTimer;
    private Intent intent;
    private TextView textViewTimer;
    public static final String EXTRA_MESSAGE = "com.example.thefactorfactor.CHECK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        intent = getIntent();
        int status = intent.getIntExtra("STATUS",0);
        if(status!=1){
            qn_no=1;
            scores=0;
            i_prev1=0;
            i_prev2=0;
        }
        arr=new long[6];
        n = intent.getLongExtra(NewGame.EXTRA_MESSAGE, -1);
        TextView textView = (TextView) findViewById(R.id.textView_qn);
        String str=Integer.toString(qn_no)+")";
        textView.setText(str);
        textView = (TextView) findViewById(R.id.textView13);
        textView.setText(Integer.toString(scores));
        textView=(TextView)findViewById(R.id.textView6);
        textView.setText(Long.toString(n));
        Random rand = new Random();

        if(savedInstanceState!=null){
            timeLeft = savedInstanceState.getLong("TIME LEFT");
            o1 = savedInstanceState.getLong("OPTION1");
            o2 = savedInstanceState.getLong("OPTION2");
            o3 = savedInstanceState.getLong("OPTION3");
            i = savedInstanceState.getInt("ANSWER");
            qn_no = savedInstanceState.getInt("QUESTION");
            scores = savedInstanceState.getInt("SCORES");
        }
        else {
            int wrg = 0;
            o1 = rand.nextInt((int) n) + 1;
            o2 = 0;
            o3 = 0;
            if (n % o1 == 0)
                i = 1;
            else
                wrg++;
            if (n > 2 && n <= 4) {
                int choice;
                if (n == 3) {
                    choice = rand.nextInt(2) + 1;
                    o1 = 2 * choice - 1;
                } else {
                    choice = rand.nextInt(3);
                    o1 = 2 * choice;
                    if (o1 == 0)
                        o1 = 1;
                }
                int arr3[][] = {{0, 0}, {0, 2}, {2, 2}};
                int arr4[][] = {{0, 0}, {0, 3}, {3, 3}};
                choice = rand.nextInt(3);
                if (n == 3) {
                    o2 = arr3[choice][0];
                    o3 = arr3[choice][1];
                } else {
                    o2 = arr4[choice][0];
                    o3 = arr4[choice][1];
                }
            }
            if (n > 4) {
                while (true) {
                    long t = rand.nextInt((int) n) + 1;
                    if (t != o1 && t != o2) {
                        if (n % t == 0) {
                            if (i == 0) {
                                if (o2 == 0) {
                                    o2 = t;
                                    i = 2;
                                } else {
                                    o3 = t;
                                    i = 3;
                                    break;
                                }
                            }
                        } else if (wrg != 2) {
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
            }

            if (i_prev1 == i || i_prev2 == i) {
                long t;
                if (i_prev1 != 1 && i_prev2 != 1) {
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
                    i = 1;
                } else if (i_prev1 != 2 && i_prev2 != 2) {
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
                } else {
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
        }
        i_prev2=i_prev1;
        i_prev1=i;
        textViewTimer=(TextView)findViewById(R.id.textView_timer);
        Button btn1 = (Button) findViewById(R.id.button_o1);
        btn1.setText(Long.toString(o1));
        Button btn2 = (Button) findViewById(R.id.button_o2);
        btn2.setText(Long.toString(o2));
        Button btn3 = (Button) findViewById(R.id.button_o3);
        btn3.setText(Long.toString(o3));
        textViewTimer.setText("Time Left : 10");
        countDown();
    }
    public void countDown(){
        countDownTimer= new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                int seconds = (int)timeLeft/1000;
                String timeLeftText = String.format(Locale.getDefault(),"%02d",seconds);
                textViewTimer.setText("Time Left : "+timeLeftText);
            }

            @Override
            public void onFinish() {
               arr[4]=0;
               displayAnswer();
            }
        }.start();
    }
    public void button1(View view)
    {
        arr[4]=1;
        displayAnswer();
    }

    public void button2(View view)
    {
        arr[4]=2;
        displayAnswer();
    }

    public void button3(View view)
    {
        arr[4]=3;
        displayAnswer();
    }
    public void displayAnswer() {

        countDownTimer.cancel();
        arr[0]=n;
        arr[1]=o1;
        arr[2]=o2;
        arr[3]=o3;
        arr[5]=i;
        if(arr[4]==arr[5])
            scores++;
        qn_no++;
        intent = new Intent(this, Answer.class);
        intent.putExtra(EXTRA_MESSAGE,arr);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
            countDownTimer.cancel();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("TIME LEFT",timeLeft);
        outState.putLong("OPTION1",o1);
        outState.putLong("OPTION2",o2);
        outState.putLong("OPTION3",o3);
        outState.putInt("ANSWER",i);
        outState.putInt("QUESTION",qn_no);
        outState.putInt("SCORES",scores);
    }
}
