package com.geniuscreations.successtaskanalyzer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna sameer on 4/1/2016.
 */
public class Schedule extends MainActivity.ArrayListFragment {

    ListView listView;
    CategoryListViewCustom categoryListViewCustom;

    List<List<Object>> tableContent = new ArrayList<List<Object>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        listView = (ListView) v.findViewById(android.R.id.list);




        for(int i=0;i<10;i++){
            tableContent.add(new ArrayList<Object>());
            for(int j=0;j<2;j++){
                tableContent.get(i).add(Integer.toString(i));
            }
        }

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryListViewCustom = new CategoryListViewCustom(getContext(),tableContent);
        listView.setAdapter(categoryListViewCustom);

    }

    public class CategoryListViewCustom extends CustomListView{

        List<List<Object>> tableContent;//store each rows contents in a nested-list,and further all the rows together as one more list

        public CategoryListViewCustom(Context context,List<List<Object>> tableContent){
            super(context,tableContent);
            this.tableContent = tableContent;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.inflater.inflate(R.layout.schedule_list_view_row, null);
            TextView tv1 = (TextView) convertView.findViewById(R.id.tvScheduleTask);
            TextView tv2 = (TextView) convertView.findViewById(R.id.tvScheduleGoal);
            tv1.setText((String)tableContent.get(position).get(0));
            tv2.setText((String)tableContent.get(position).get(1));
            return convertView;
        }
    }

}
