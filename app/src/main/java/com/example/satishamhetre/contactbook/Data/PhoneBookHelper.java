package com.example.satishamhetre.contactbook.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Satish A. Mhetre on 16-06-2017.
 */

public class PhoneBookHelper extends SQLiteOpenHelper {


   private final static String DATABASE_NAME ="phonebook.db";

    private static final int DATABASE_VERSION =1;

    public PhoneBookHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = " CREATE TABLE " +
                Contract.Entry.TABLE_NAME + " ( " +
                Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Contract.Entry.COLUMN_PERSON_NAME + " TEXT NOT NULL , " +
                Contract.Entry.COLUMN_PERSON_NUMBER + " TEXT NOT NULL ) ";


       db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
