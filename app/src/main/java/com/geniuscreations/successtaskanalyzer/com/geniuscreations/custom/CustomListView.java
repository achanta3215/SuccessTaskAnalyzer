package com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geniuscreations.successtaskanalyzer.MainActivity;
import com.geniuscreations.successtaskanalyzer.R;

import java.util.List;

/**
 * Created by Krishna sameer on 5/7/2016.
 */
public class CustomListView extends BaseAdapter {

    Context context;

    protected List<List<Object>> tableContent;
    protected LayoutInflater inflater = null;

    public CustomListView(Context context,List<List<Object>> tableContent){
        this.context = context;
        this.tableContent = tableContent;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return tableContent.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return tableContent.get(position);
    }
}
