package com.geniuscreations.successtaskanalyzer.com.geniuscreations.database;

import android.provider.BaseColumns;

/**
 * Created by Krishna sameer on 4/3/2016.
 */
public final class DbContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TaskData";

    private DbContract(){}

    public static abstract class TableCategory implements BaseColumns{
        public static final String TABLE_NAME = "Category";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_REJECTED = "Rejected";
        public static final String COLUMN_ACCEPTED = "Accepted";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_REJECTED + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_ACCEPTED + INTEGER_TYPE +
                " )";


    }

    public static abstract class TableTask implements BaseColumns{
        public static final String TABLE_NAME = "Task";
        public static final String COLUMN_CATEGORY_NAME = "Category_name";
        public static final String COLUMN_TITLE = "Title";
        public static final String COLUMN_TIME = "Time";
        public static final String COLUMN_STATUS = "Status";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_TIME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_STATUS + INTEGER_TYPE +
                        " )";
    }


}
