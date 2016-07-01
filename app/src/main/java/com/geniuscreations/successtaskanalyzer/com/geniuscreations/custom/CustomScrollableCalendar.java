package com.geniuscreations.successtaskanalyzer.com.geniuscreations.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.Toast;

/**
 * Created by Krishna sameer on 6/12/2016.
 */
public class CustomScrollableCalendar extends sun.bob.mcalendarview.MCalendarView {


    Context context;

    public CustomScrollableCalendar(Context context) {
        super(context);
        this.context = context;
    }



    public CustomScrollableCalendar(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
            Toast.makeText(context,"Insider",Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
