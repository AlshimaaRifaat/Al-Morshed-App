package com.example.shosho.almorshed.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shosho.almorshed.model.Quran;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper{
    SQLiteDatabase sqLiteDatabase;
    Context context;

    private static final String DATABASE_NAME="QuranDB";
    private static final String TABLE_NAME = "QuranTable";
    private static final int DATABASE_VERSION=1;

    private static final String word1="word1";
    private static final String word2="word2";
    private static final String meaning="meaning";
    private static final String souret="souret";
    private static final String part="part";

private static final String CREATE_TABLE="CREATE TABLE QuranTable ( word1  VARCHAR(255) , word2 VARCHAR(255) , meaning VARCHAR(255) ,souret VARCHAR(255),part  VARCHAR(255) )";
private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
public DbHelper(Context context)
{
    super( context,DATABASE_NAME,null,1);
    sqLiteDatabase=this.getWritableDatabase();
    this.context=context;
}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL( DROP_TABLE );
    onCreate( sqLiteDatabase );
    }
    public void insertData(ArrayList<Quran> quranList)
    {
        ContentValues contentValues=new ContentValues( );
       for(Quran q: quranList) {
           contentValues.put(DbHelper.word1, q.getWord1());
           contentValues.put( DbHelper.word2,q.getWord2() );
           contentValues.put( DbHelper.meaning,q.getMeaning() );
           contentValues.put( DbHelper.souret,q.getSouret() );
           contentValues.put( DbHelper.part,q.getPart() );
           sqLiteDatabase.insert( DbHelper.TABLE_NAME,null,contentValues );
       }
    }
    public ArrayList<Quran> getData(String Word)
    {
        /*Cursor cursor=db.rawQuery( "select "+DbHelper.meaning+" ,"+ DbHelper.souret+" ,"
                +DbHelper.part+" from "+DbHelper.TABLE_NAME+" where "+DbHelper.word1+"="+Word+" or " +
        DbHelper.word2+" = "+Word+";",null);
        */
        ArrayList<Quran> quranArrayList=new ArrayList<>(  );

        String[]Columns={DbHelper.word1,DbHelper.word2,DbHelper.meaning,DbHelper.souret,DbHelper.part};
        String[]selectionArgs={Word,Word};
        Cursor cursor=sqLiteDatabase.query( DbHelper.TABLE_NAME,Columns,"word1=? or word2=?",selectionArgs,
                null ,null,null);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Quran quran=new Quran( cursor.getString( 0 ),cursor.getString( 1 ),cursor.getString( 2 ),
                        cursor.getString( 3 ),cursor.getString( 4 ));
                quranArrayList.add( quran );
            }
        }
        return quranArrayList;
    }
}
