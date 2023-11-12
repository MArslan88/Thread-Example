package com.mhdarslan.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity - ";
    private Button btnTest, btnStart, btnStop;
    private int clickCount;
    private boolean stopTask = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTest = findViewById(R.id.btnTest);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnTest.setOnClickListener(v -> {
            clickCount++;
            btnTest.setText("Click to test UI: " + clickCount);
        });

        btnStart.setOnClickListener(v -> {
            startThread();
        });
        
        btnStop.setOnClickListener(v -> {
            stopThread();
        });
    }

    private void stopThread() {
        stopTask = true;
    }

    private void startThread() {
        stopTask = false; // start again because condition is false
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if(stopTask){return;};
                    Log.d(TAG, "startTread: "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }


}