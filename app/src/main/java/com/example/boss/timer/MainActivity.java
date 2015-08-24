package com.example.boss.timer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private Timer timer;
    TextView timerText;
    int currentPeriod = 0;
    Date startDate;
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.d("myLogs", "---onCreate---");
        timerText = (TextView)findViewById(R.id.time_text);
        if (savedInstanceState == null) {
            timerText.setText("00:00.00");
        } else {
            onTimerStart(startTime);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("start_time", startTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        startTime = savedInstanceState.getLong("start_time");
    }

    public void onClick(View view) {
        if (timer != null) {
            timer.cancel();
            timerText.setText("00:00.00");
        }
        startDate = new Date();
        startTime = startDate.getTime();
        onTimerStart(startTime);
    }

    private void onTimerStart (long start){
        startTime = start;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date newDate = new Date();
                long dateForTimer = newDate.getTime() - startTime;
                final String temp = (new SimpleDateFormat("mm:ss.SS")).format(dateForTimer);
                currentPeriod++;
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerText.setText(temp);
                    }
                });
            }
        }, 0, 10);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myLogs", "---onRestart---");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("myLogs", "---onResume---");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myLogs", "---onPause---");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myLogs", "---onStop---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myLogs", "---onDestroy---");
    }


}
