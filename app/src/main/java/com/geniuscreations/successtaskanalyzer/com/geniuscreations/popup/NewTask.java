package com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.geniuscreations.successtaskanalyzer.R;

/**
 * Created by Krishna sameer on 5/17/2016.
 */
public class NewTask extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_task, null);


        //EditText et = new EditText();



        return view;
    }
}
