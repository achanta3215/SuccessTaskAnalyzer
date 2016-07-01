package com.geniuscreations.successtaskanalyzer;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.geniuscreations.successtaskanalyzer.com.geniuscreations.alarm.ScheduleAlarm;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomActivityToolbar;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomCursorAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomListViewContextMenu;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbContract;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.interfaceMe.EditTaskConstants;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.interfaceMe.WeeklyTaskConstants;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.models.Task;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup.BooleanDialog;

import java.util.Set;

/**
 * Created by Krishna sameer on 4/1/2016.
 */
public class Weekly extends MainActivity.ArrayListFragment {



    Context context;
    ListView listView;
    TextView emptyListView;
    CustomCursorAdapter customAdapter;
    DbAdapter db;
    Cursor mAdapter;
    Task task;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();



        View v = inflater.inflate(R.layout.list_view, container, false);
        listView = (ListView) v.findViewById(android.R.id.list);
        emptyListView = (TextView) v.findViewById(android.R.id.empty);
        emptyListView.setText("No Scheduled tasks");


        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = DbAdapter.openDb(context);


        task = new Task(context, null,WeeklyTaskConstants.where,WeeklyTaskConstants.whereArgs,WeeklyTaskConstants.orderBy,db);
        mAdapter = task.getCursorQueryDb();

        new Handler().post(new Runnable() {

                               @Override
                               public void run() {
                                   customAdapter = new WeeklyCustomCursorAdapter(
                                           context,
                                           mAdapter,
                                           0);

                                   listView.setAdapter(customAdapter);
                                   getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

                                   getListView().setMultiChoiceModeListener(new WeeklyCustomListViewContextMenu(context,getActivity(),customAdapter));

                                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                           Intent intent = new Intent(context,TaskViewStats.class);
                                           Bundle extras = new Bundle();
                                           extras.putLong("ID",id);
                                           extras.putStringArrayList(EditTaskConstants.ARRAY_LIST_NAME,task.getAttributes(position));
                                           intent.putExtras(extras);
                                           startActivity(intent);


                                       }
                                   });

                                   listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                       @Override
                                       public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                           getListView().setItemChecked(position,!customAdapter.isPositionChecked(position));

                                           return false;
                                       }
                                   });




                               }
                           }
        );

    }


    private class WeeklyCustomListViewContextMenu extends CustomListViewContextMenu {



            Activity activity;
            Context context;
            CustomCursorAdapter mAdapter;


            public WeeklyCustomListViewContextMenu(Context context, Activity activity, CustomCursorAdapter mAdapter) {
                super(context, activity, mAdapter);
                this.activity = activity;
                this.context = context;
                this.mAdapter = mAdapter;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                nr = 0;
                MenuInflater menuInflater = activity.getMenuInflater();
                menuInflater.inflate(R.menu.context_delete,menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_delete:
                        nr = 0;
                        final Set<String> currentSelection_rowID = mAdapter.get_mId();
                        mAdapter.clearSelection();
                        mode.finish();


                        BooleanDialog booleanDialog = new BooleanDialog(context,"Confirm Delete","Are you sure?");
                        booleanDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        booleanDialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mAdapter.removeFromDataBase(currentSelection_rowID,db,DbContract.TableTask.TABLE_NAME);
                                ScheduleAlarm.removeAlarms(currentSelection_rowID,context);
                                Cursor c =  task.getCursorQueryDb();
                                mAdapter.customNotifyDataSetChanged(c,customAdapter,getActivity());

                            }
                        });
                        booleanDialog.show();


                        return true;
                }
                return false;
            }



    }

    private class WeeklyCustomCursorAdapter extends CustomCursorAdapter{

        public WeeklyCustomCursorAdapter(Context context, Cursor mAdapter, int flags) {
            super(context,mAdapter,flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return Task.renderViewWeeklyTask(parent,cursorInflater);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Task.attachToViewWeeklyTask(cursor,view);
        }
    }





}
