package com.harbin.fortriplebyte;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harbin.fortriplebyte.database.TransactionContract;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    private SQLiteDatabase mDb;




    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name;
        public TextView tv_cost;
        public TextView tv_date;

        public ViewHolder(View itemview){
            super(itemview);
            tv_name = (TextView) itemview.findViewById(R.id.tv_name);
            tv_cost = (TextView) itemview.findViewById(R.id.tv_cost);
            tv_date = (TextView) itemview.findViewById(R.id.tv_date);
        }
    }

    public TransactionsAdapter(){

    }

    public TransactionsAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    public TransactionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_transactions_adapter, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }

        final long sqlId = cursor.getLong(cursor.getColumnIndex(TransactionContract.TransactionEntry._ID));
        final String name = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME));
        final String date = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_DATE));
        final double cost = cursor.getDouble(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_COST));


        holder.tv_date.setText(date);
        holder.tv_cost.setText(String.valueOf(cost));
        holder.tv_name.setText(name);
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    public void swapCursor(Cursor newCursor){
        if(cursor != null) cursor.close();
        cursor = newCursor;
        if(newCursor != null){
            this.notifyDataSetChanged();
        }
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


