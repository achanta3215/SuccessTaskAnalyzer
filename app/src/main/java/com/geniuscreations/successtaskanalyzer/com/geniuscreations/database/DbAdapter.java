package com.geniuscreations.successtaskanalyzer.com.geniuscreations.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Krishna sameer on 4/3/2016.
 */
public class DbAdapter {


    private final Context context;

    private DatabaseHelper myDbHelper;
    private SQLiteDatabase db;

    public DbAdapter(Context context){
        this.context = context;
        myDbHelper = new DatabaseHelper(context);
    }

    public DbAdapter open(){
        db = myDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myDbHelper.close();
    }





}
