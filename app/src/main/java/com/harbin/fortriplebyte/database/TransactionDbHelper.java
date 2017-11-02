package com.harbin.fortriplebyte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Harbin on 11/2/17.
 */

public class TransactionDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "transactions.db";
    private static final int DATABASE_VERSION = 4;

    public TransactionDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RECORD_TABLE = " CREATE TABLE " + TransactionContract.TransactionEntry.TABLE_NAME + " (" +
                TransactionContract.TransactionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TransactionContract.TransactionEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                TransactionContract.TransactionEntry.COLUMN_COST + " FLOAT NOT NULL, " +
                TransactionContract.TransactionEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                TransactionContract.TransactionEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TransactionContract.TransactionEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        db.execSQL(SQL_CREATE_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TransactionContract.TransactionEntry.TABLE_NAME);
        onCreate(db);
    }
}
