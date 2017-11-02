package com.harbin.fortriplebyte;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.harbin.fortriplebyte.database.TransactionContract;
import com.harbin.fortriplebyte.database.TransactionDbHelper;

public class TranscationActivity extends AppCompatActivity {


    private RecyclerView mRecycleView;
    private TransactionsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation);

        mRecycleView = (RecyclerView) findViewById(R.id.rv_transaction);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        TransactionDbHelper dbHelper = new TransactionDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllTransactions();

        mAdapter = new TransactionsAdapter(this, cursor);
        mRecycleView.setAdapter(mAdapter);

//        addTempData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TranscationActivity.this, AddTransactionActivity.class));
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        mAdapter.swapCursor(getAllTransactions());

    }

    private void addTempData(){
        ContentValues cv = new ContentValues();
        cv.put(TransactionContract.TransactionEntry.COLUMN_TYPE, "Food");
        cv.put(TransactionContract.TransactionEntry.COLUMN_NAME, "Dinner at Momo's");
        cv.put(TransactionContract.TransactionEntry.COLUMN_COST, 17.35);
        cv.put(TransactionContract.TransactionEntry.COLUMN_DATE, "7/28/17");
        mDb.insert(TransactionContract.TransactionEntry.TABLE_NAME, null, cv);

        Cursor cursor = getAllTransactions();
        mAdapter.swapCursor(cursor);


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
