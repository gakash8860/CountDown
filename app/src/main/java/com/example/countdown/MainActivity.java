package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private boolean timerrunnung;
    private long mtimeleft=START_TIME_IN_MILLI;
     Button start,reset;
     TextView countdown;
    private static final long START_TIME_IN_MILLI=600000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        reset=findViewById(R.id.reset);
        countdown=findViewById(R.id.count);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(timerrunnung){
                pausetimer();
            }else{
                starttimer();
            }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resettimer();

            }
        });
        updateCountDownText();

    }

    private void starttimer(){
        countDownTimer = new CountDownTimer(mtimeleft,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mtimeleft=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
            timerrunnung=false;
            start.setText("Start");
            start.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.VISIBLE);
            }
        }.start();
        timerrunnung=true;
        start.setText("Pause");
        reset.setVisibility(View.INVISIBLE);

    }
    private void pausetimer(){

         countDownTimer.cancel();
         timerrunnung=false;
         start.setText("Start");
         reset.setVisibility(View.VISIBLE);

    }
    private void resettimer(){
        mtimeleft=START_TIME_IN_MILLI;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText(){
        int minutes=(int) mtimeleft/1000/60;
        int seconds=(int) mtimeleft/1000%60;

        String timeleftformatted = String.format(Locale.getDefault() ,"%02d:%02d",minutes,seconds);
        countdown.setText(timeleftformatted);

    }
}