package com.example.a111.ibsweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        final int welcomeScreenDisplay = 4000;
        Thread welcomeThread = new Thread() {
            int wait = 0;

            @Override
            public void run() {
                try {
                    super.run();
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {

                    startActivity(new Intent(SplashScreen.this,Main2Activity.class));
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}