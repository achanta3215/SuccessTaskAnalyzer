package com.geniuscreations.successtaskanalyzer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.ListView;
import android.support.design.widget.TabLayout;

import com.geniuscreations.successtaskanalyzer.com.geniuscreations.interfaceMe.RefreshAdapter;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup.NewDialog;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup.NewGoal;
import com.geniuscreations.successtaskanalyzer.com.geniuscreations.popup.NewTask;

/**
 * Created by Krishna sameer on 3/15/2016.
 */
public class MainActivity extends AppCompatActivity implements RefreshAdapter{

    static final int NUM_ITEMS = 3;
    static final String[] titles = {"Today","Scheduled","Goals"};
    MyAdapter mAdapter;
    static Goals goal;
    ViewPager mPager;
    private TabLayout tabLayout;
    int currentPagePosition = 0;
    RefreshAdapter refreshAdapter;
    Context context;
    @Override
    public void refreshAdapter(String s) {

        Toast.makeText(context,"Entered here",Toast.LENGTH_SHORT).show();
        switch (s){
            case RefreshAdapter.Goals:
                refreshAdapter = (Goals) goal;
                refreshAdapter.refreshAdapter("");
                break;
            case RefreshAdapter.Task:
                break;
        }
    }

    enum TITLES {TODAY,SCHEDULED,GOALS}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Action bar onCreate
        // TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){
            case R.id.action_new:
                String currentSelectedItem = titles[currentPagePosition];
                android.app.FragmentManager manager = getFragmentManager();
                NewDialog newDialog = null;
                switch (TITLES.valueOf(currentSelectedItem.toUpperCase())){
                    case TODAY:
                        Intent intent = new Intent(this, com.geniuscreations.successtaskanalyzer.NewTask.class);
                        startActivity(intent);
                        //newDialog = new NewTask();
                        //newDialog.show(manager,"NewTask");
                        return true;
                    case SCHEDULED:
                        break;
                    case GOALS:
                        newDialog = new NewGoal();
                        Bundle args = new Bundle();
                        newDialog.show(manager,"NewGoal");
                        return true;
                }
                break;
            default:
                break;
        }




        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Hi","Oye");
        super.onCreate(savedInstanceState);

        context = getBaseContext();
        setContentView(R.layout.activity_main);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.mainToolBar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setTitle("TASK ANALYZER");


        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pagerMain);
        mPager.setAdapter(mAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPagePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public static class ArrayListFragment extends ListFragment {

    int mNum;
        static ArrayListFragment newInstance(int num) {

            ArrayListFragment f = null;
            Log.i("Hi","Hi");
            switch(num){
                case 0: f = new TodayTask();
                    break;
                case 1: f = new Weekly();
                    break;
                case 2 : goal = new Goals();
                         f = goal;
                    break;
            }


            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {//The number associated with the view is retrieved
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {//generates a view for the current screen
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView)tv).setText("Fragment #" + mNum);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {//called once the activity is created, used to do some final initialization stuff
            super.onActivityCreated(savedInstanceState);
            //setListAdapter(new ArrayAdapter<String>(getActivity(),
            //android.R.layout.simple_list_item_1, new String[]{"r","s","t"}));
        }


    }


    public static class ListViewCustom extends BaseAdapter{


        /*public ListViewCustom(Context context, String[] data){

        }*/


        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }




}
