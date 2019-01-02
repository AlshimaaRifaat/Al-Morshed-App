package com.example.shosho.almorshed.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "morshedDb";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query("part15", null, null, null, null, null, null);
    }


}


   /*SQLiteDatabase sqLiteDatabase;
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
public DatabaseHelper(Context context)
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
           contentValues.put(DatabaseHelper.word1, q.getWord1());
           contentValues.put( DatabaseHelper.word2,q.getWord2() );
           contentValues.put( DatabaseHelper.meaning,q.getMeaning() );
           contentValues.put( DatabaseHelper.souret,q.getSouret() );
           contentValues.put( DatabaseHelper.part,q.getPart() );
           sqLiteDatabase.insert( DatabaseHelper.TABLE_NAME,null,contentValues );
       }
    }
    public ArrayList<Quran> getData(String Word)
    {
        *//*Cursor cursor=db.rawQuery( "select "+DatabaseHelper.meaning+" ,"+ DatabaseHelper.souret+" ,"
                +DatabaseHelper.part+" from "+DatabaseHelper.TABLE_NAME+" where "+DatabaseHelper.word1+"="+Word+" or " +
        DatabaseHelper.word2+" = "+Word+";",null);
        *//*
        ArrayList<Quran> quranArrayList=new ArrayList<>(  );

        String[]Columns={DatabaseHelper.word1,DatabaseHelper.word2,DatabaseHelper.meaning,DatabaseHelper.souret,DatabaseHelper.part};
        String[]selectionArgs={Word,Word};
        Cursor cursor=sqLiteDatabase.query( DatabaseHelper.TABLE_NAME,Columns,"word1=? or word2=?",selectionArgs,
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
    }*/


