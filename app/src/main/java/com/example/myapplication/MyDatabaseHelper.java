package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "VocabularyBook.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_vocabulary";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_eng = "vocabulary_eng";
    private static final String COLUMN_ch = "vocabulary_ch";
    //private static final String COLUMN_PAGES = "book_pages";

    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_eng + " TEXT, " +
                COLUMN_ch + " TEXT) ;";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i,int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addVocabulary(String eng, String ch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_eng, eng);
        cv.put(COLUMN_ch, ch);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "添加失敗!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "添加成功!", Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
