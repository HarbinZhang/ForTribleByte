package com.harbin.fortriplebyte.database;

import android.provider.BaseColumns;

/**
 * Created by Harbin on 11/2/17.
 */

public class TransactionContract {
    public static final class TransactionEntry implements BaseColumns{
        public static final String TABLE_NAME = "transactions";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_NAME = "name";

    }
}
