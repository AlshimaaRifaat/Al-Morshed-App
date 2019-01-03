package com.example.shosho.almorshed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
;import com.example.shosho.almorshed.database.DatabaseHelper;
import com.example.shosho.almorshed.model.Quran;

import java.io.IOException;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
public static DatabaseHelper databaseHelper;

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
         databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            databaseHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        }

}
