package com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.geniuscreations.successtaskanalyzer.R;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomDatabaseSpinnerAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomTimeTextWatcher;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.database.DbContract;

/**
 * Created by Krishna sameer on 5/17/2016.
 */
public class NewTask extends NewDialog {


    Context context;
    EditText time;

    Spinner goals;
    DbAdapter db;
    SpinnerAdapter goalsSpinnerAdapter;
    long selectedGoalId;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        context = getActivity();

        View view = inflater.inflate(R.layout.new_task, null);

        goals = (Spinner) view.findViewById(R.id.spinnerNewTask);
        time = (EditText) view.findViewById(R.id.editTextTimeNewTask);



        db = DbAdapter.openDb(context);
        final Cursor c = db.getGoals();

        //time.addTextChangedListener(new CustomTimeTextWatcher() );



        goals.setAdapter(new CustomDatabaseSpinnerAdapter(context,getActivity(),c, DbContract.TableGoal.COLUMN_NAME));

        goals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGoalId = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       /* scheduleBoolean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!isChecked){
                    fromTime.setVisibility(View.GONE);
                    toTextView.setVisibility(View.GONE);
                    toTime.setVisibility(View.GONE);

                }
                else{
                    fromTime.setVisibility(View.VISIBLE);
                    toTextView.setVisibility(View.VISIBLE);
                    toTime.setVisibility(View.VISIBLE);

                }

            }
        });*/








        return view;
    }
}
