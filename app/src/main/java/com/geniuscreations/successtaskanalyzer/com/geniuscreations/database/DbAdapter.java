package com.geniuscreations.successtaskanalyzer.com.geniuscreations.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Set;

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

    public static DbAdapter openDb(Context context){
        DbAdapter db = new DbAdapter(context);
        db.open();
        return db;
    }

    public DbAdapter open(){
        db = myDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myDbHelper.close();
    }

    public long insertGoal(String goalName){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.TableGoal.COLUMN_NAME,goalName);
        return db.insert(DbContract.TableGoal.TABLE_NAME,null,contentValues);


    }

    public long insertTask(String task_name, String goal_name, String description, String time, String status, String schedule, int repeatOn, int accept, int reject){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.TableTask.COLUMN_TITLE,task_name);
        contentValues.put(DbContract.TableTask.COLUMN_GOAL_NAME,goal_name);
        contentValues.put(DbContract.TableTask.COLUMN_DESCRIPTION,description);
        contentValues.put(DbContract.TableTask.COLUMN_TIME,time);
        contentValues.put(DbContract.TableTask.COLUMN_STATUS,status);
        contentValues.put(DbContract.TableTask.COLUMN_SCHEDULE,schedule);
        contentValues.put(DbContract.TableTask.COLUMN_REPEAT,repeatOn);
        contentValues.put(DbContract.TableTask.COLUMN_ACCEPTED,accept);
        contentValues.put(DbContract.TableTask.COLUMN_REJECTED,reject);
        return db.insert(DbContract.TableTask.TABLE_NAME,null,contentValues);

    }



    public Cursor getTask(long id){
        String tableName = DbContract.TableTask.TABLE_NAME;
        String[] columns = {DbContract.TableTask.COLUMN_ID,DbContract.TableTask.COLUMN_TITLE,DbContract.TableTask.COLUMN_GOAL_NAME,DbContract.TableTask.COLUMN_DESCRIPTION,DbContract.TableTask.COLUMN_TIME,DbContract.TableTask.COLUMN_ACCEPTED,DbContract.TableTask.COLUMN_REJECTED};
        String where = DbContract.TableTask.COLUMN_ID + "=?";
        String[] whereArgs = {Long.toString(id)};
        return db.query(false,tableName,columns,where,whereArgs,null,null,null,null);
    }

    public Cursor getGoals(){

        String tableName = DbContract.TableGoal.TABLE_NAME;
        String[] columns = {DbContract.TableGoal.COLUMN_ID,DbContract.TableGoal.COLUMN_NAME};
        Cursor c = db.query(false,tableName,columns,null,null,null,null,null,null);

        return c;
    }

    public Cursor getTask(){

        String tableName = DbContract.TableTask.TABLE_NAME;
        String[] columns = {DbContract.TableTask.COLUMN_ID,DbContract.TableTask.COLUMN_TITLE,DbContract.TableTask.COLUMN_GOAL_NAME,DbContract.TableTask.COLUMN_TIME};
        return db.query(false,tableName,columns,null,null,null,null,null,null);
    }

    public Cursor getTask(String[] columns,String where,String[] whereArgs,String orderBy){

        String tableName = DbContract.TableTask.TABLE_NAME;
        return db.query(false,tableName,columns,where,whereArgs,null,null,orderBy,null);
    }




    public void updateTaskStatus(long id,int accepted){
        String table = DbContract.TableTask.TABLE_NAME;
        String column1 = DbContract.TableTask.COLUMN_ACCEPTED;
        String whereClause = "WHERE _ID='"+id+"'";
        String updateSuccess = "UPDATE "+ table + " SET " + column1 +" = " + column1 + "+1 " + whereClause + ";";
        String updateFailure = "UPDATE "+ table + " SET " + column1 +" = " + column1 + "+1 " + whereClause + ";";

        if(accepted==1){
            db.execSQL(updateSuccess);
        }else{
            db.execSQL(updateFailure);
        }

    }





    public void removeFromTableName(Set<String> id,String tableName){
        String table = tableName;

        StringBuilder placeHolders = new StringBuilder();
        int size = id.size();

        String[] whereArgs = new String[size];

        id.toArray(whereArgs);

        for (int i = 0; i < size; i++) {
            placeHolders.append("_id="+whereArgs[i]);
            if(i!=size-1)
                placeHolders.append(" or ");
        }

        Toast.makeText(context,placeHolders,Toast.LENGTH_SHORT).show();
        db.execSQL("delete from "+ table + " where "+ placeHolders );

    }





}
