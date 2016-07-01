package com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.geniuscreations.successtaskanalyzer.R;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbContract;

import java.util.ArrayList;

/**
 * Created by Krishna sameer on 5/31/2016.
 */
public class CustomDatabaseSpinnerAdapter implements SpinnerAdapter {

    ArrayList<String> arrayList;
    ArrayList<Long> idList;
    Context context;
    LayoutInflater layoutInflater;
    View v;
    TextView tv;

    public CustomDatabaseSpinnerAdapter(Context context, Activity activity, Cursor c, String queryColumn){
        this.context = context;
        arrayList = new ArrayList<String>();
        idList = new ArrayList<Long>();
        layoutInflater = activity.getLayoutInflater();
        //Log.d(DatabaseUtils.dumpCursorToString(c),"Database dump");
        Log.i("q3",Integer.toString(c.getColumnIndex("Name")));


        Toast.makeText(context,queryColumn,Toast.LENGTH_SHORT).show();
        if(c.moveToFirst()){
            do{


                //c.getType()
                String x = c.getString(c.getColumnIndex(DbContract.TableGoal.COLUMN_NAME));
                //c.get
                arrayList.add(x);
                idList.add(c.getLong(c.getColumnIndexOrThrow("_id")));//change hardcoded reference
            }while(c.moveToNext());

        }
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if(convertView!=null){
            v = convertView;
        }else{
            v = layoutInflater.inflate(R.layout.single_text_view,null);
        }
        tv = (TextView) v.findViewById(R.id.tvSingleTextView);
        tv.setText(arrayList.get(position));
        return v;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return idList.get(position);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView!=null){
            v = convertView;
        }else{
            v = layoutInflater.inflate(R.layout.single_text_view,null);
        }
        tv = (TextView) v.findViewById(R.id.tvSingleTextView);
        tv.setText(arrayList.get(position));
        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
