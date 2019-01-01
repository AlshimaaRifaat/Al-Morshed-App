package com.example.shosho.almorshed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
;import com.example.shosho.almorshed.database.DbHelper;
import com.example.shosho.almorshed.model.Quran;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    boolean createDB=false;
    ArrayList<Quran> quran;
    public static DbHelper db;

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

        SharedPreferences sp = getSharedPreferences("DataBase", Context.MODE_PRIVATE);
        createDB=sp.getBoolean("open",false);
        db=new DbHelper(this);
        if(createDB==false){

            //read xls file here-----------------------------------------------
            quran=new ArrayList<>();
            Quran temp=new Quran();
            temp=new Quran("ٱلۡعَٰلَمِينَ", "العلمين", "جميع العوالم", "سورة الفاتحة", "لجزء الاول");
            quran.add(temp);
            temp=new Quran("رَبِّ قَدۡ ءَاتَيۡتَنِي مِنَ ٱلۡمُلۡكِ","رب قد ءاتيتني من الملك","يا مالكي ومدبر أمري قد أعطيتني من الملك","سورة يوسف","الجزء الثالث عشر");
            quran.add(temp);
            db.insertData(quran);
            sp.edit().putBoolean("open",true).commit();
        }


    }
}
