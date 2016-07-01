package com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Krishna sameer on 5/27/2016.
 */
public abstract class NewDialog extends DialogFragment {



    @Nullable
    @Override
    abstract public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
