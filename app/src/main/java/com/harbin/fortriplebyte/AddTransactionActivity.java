package com.harbin.fortriplebyte;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.harbin.fortriplebyte.database.TransactionContract;
import com.harbin.fortriplebyte.database.TransactionDbHelper;

public class AddTransactionActivity extends AppCompatActivity {


    private SQLiteDatabase mDb;

    private AutoCompleteTextView et_name, et_type, et_cost, et_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        et_name = (AutoCompleteTextView) findViewById(R.id.et_name);
        et_cost = (AutoCompleteTextView) findViewById(R.id.et_cost);
        et_date = (AutoCompleteTextView) findViewById(R.id.et_date);
        et_type = (AutoCompleteTextView) findViewById(R.id.et_type);

        TransactionDbHelper dbHelper = new TransactionDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
    }

    public void AddTransaction(View v){
        ContentValues cv = new ContentValues();
        cv.put(TransactionContract.TransactionEntry.COLUMN_TYPE, et_type.getText().toString());
        cv.put(TransactionContract.TransactionEntry.COLUMN_NAME, et_name.getText().toString());
        cv.put(TransactionContract.TransactionEntry.COLUMN_COST, Double.parseDouble(et_cost.getText().toString()));      // changed late.
        cv.put(TransactionContract.TransactionEntry.COLUMN_DATE, et_date.getText().toString());
        mDb.insert(TransactionContract.TransactionEntry.TABLE_NAME, null, cv);

        finish();
    }

    private Cursor getAllTransactions(){
        return mDb.query(
                TransactionContract.TransactionEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

}
