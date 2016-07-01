package com.geniuscreations.successtaskanalyzer;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomCursorAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomListView;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbContract;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.functional.BooleanChoose;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.functional.CalendarConvenience;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.interfaceMe.TodayTaskConstants;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.interfaceMe.WeeklyTaskConstants;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.models.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Krishna sameer on 4/1/2016.
 */
public class TodayTask extends MainActivity.ArrayListFragment {

    ListView listView;

    CustomCursorAdapter customAdapter;
    DbAdapter db;
    Cursor mAdapter;
    Context context;
    TextView emptyListView;
    Task task;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();



        View v = inflater.inflate(R.layout.list_view, container, false);
        listView = (ListView) v.findViewById(android.R.id.list);
        emptyListView = (TextView) v.findViewById(android.R.id.empty);
        emptyListView.setText("Add a Task");


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int indexSearch = CalendarConvenience.getTodayInArrayIndex();
        StringBuilder wildcard= new StringBuilder();
        for(int i=0;i<7;i++){
            if(i==indexSearch)
                wildcard.append(BooleanChoose.Y);
            else
                wildcard.append("_");
        }

        db = DbAdapter.openDb(context);
        String[] whereArgs = {wildcard.toString()};
        task = new Task(context, TodayTaskConstants.columns,TodayTaskConstants.where,whereArgs,TodayTaskConstants.orderBy,db);


        mAdapter = task.getCursorQueryDb();


        new Handler().post(new Runnable() {

                               @Override
                               public void run() {
                                   customAdapter = new TaskCustomCursorAdapter(
                                           context,
                                           mAdapter,
                                           0);

                                   listView.setAdapter(customAdapter);
                               }
                           }
            );


    }

    private class TaskCustomCursorAdapter extends CustomCursorAdapter{

        private boolean repeatOn = false;

        public TaskCustomCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);


        }

        @Override
        public int getItemViewType(int position) {
            return Task.isRepeatOn((Cursor)getItem(position))?1:0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            repeatOn = Task.isRepeatOn(cursor);
            return Task.renderViewTodayTask(repeatOn,parent,cursorInflater);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            repeatOn = Task.isRepeatOn(cursor);
            Task.attachToViewTodayTask(cursor,repeatOn,view,context);



        }
    }



}
