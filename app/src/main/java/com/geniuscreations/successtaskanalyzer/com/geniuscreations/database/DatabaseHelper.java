package com.geniuscreations.successtaskanalyzer.com.geniuscreations.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Krishna sameer on 4/3/2016.
 */

 class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context){
            super(context,DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DbContract.TableGoal.SQL_CREATE_TABLE);
            db.execSQL(DbContract.TableTask.SQL_CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }




