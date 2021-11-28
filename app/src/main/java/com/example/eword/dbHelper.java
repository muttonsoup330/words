package com.example.eword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public static final String TB_NAME="words";
    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
                TB_NAME+"( _id integer primary key autoincrement,"+
                "word varchar,"+
                "mean varchar,"+
                "egg varchar"+
                ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion){
        db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(db);
    }
}