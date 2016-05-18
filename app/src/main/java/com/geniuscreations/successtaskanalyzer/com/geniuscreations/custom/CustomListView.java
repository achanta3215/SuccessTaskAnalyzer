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

    protected List<List<Object>> tableContent1;
    String[][] tableContent;
    protected LayoutInflater inflater = null;

    public CustomListView(Context context,List<List<Object>> tableContent1){
        this.context = context;
        this.tableContent = tableContent;
        this.tableContent1 = tableContent1;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.i("Hi","hi to you too");
    }
    @Override
    public int getCount() {
        return tableContent1.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*convertView = inflater.inflate(R.layout.list_view_row, null);
        TextView tv1 = (TextView) convertView.findViewById(R.id.tvBunkRecommender1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tvBunkRecommender2);
        tv1.setText(tableContent[position][0]);
        tv2.setText(tableContent[position][1]);
        return convertView;*/
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return tableContent1.get(position);
    }
}
