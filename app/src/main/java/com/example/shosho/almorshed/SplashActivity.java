package com.example.shosho.almorshed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        Thread timer=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(SplashActivity.this,NavigationActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
