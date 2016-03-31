package com.geniuscreations.successtaskanalyzer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Krishna sameer on 3/31/2016.
 */
abstract public class ToolBarDefault extends AppCompatActivity {




    public void setUpToolBar(ToolBarDefault toolBar){

        Toolbar myToolBar = (Toolbar) findViewById(R.id.mainToolBar);
        toolBar.setSupportActionBar(myToolBar);

    }


}
