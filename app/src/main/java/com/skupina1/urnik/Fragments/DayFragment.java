package com.skupina1.urnik.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libdata.MyData;
import com.example.libdata.urnikSchema;
import com.skupina1.urnik.ApplicationMy;
import com.skupina1.urnik.DayAdapter;
import com.skupina1.urnik.OnSwipeListener;
import com.skupina1.urnik.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class DayFragment extends Fragment {
    ApplicationMy app;
    ArrayList<urnikSchema> timetable;
    Date displayDate;
    View v;
    static String DEBUG_TAG = "ActivityDay";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        app = (ApplicationMy) getActivity().getApplication();
        v =  inflater.inflate(R.layout.activity_day, container, false);
        displayDate= Calendar.getInstance().getTime();
        dayForDate();

        return v;
    }

    public void dayUp(View v1){
        ScrollView parent = ((ScrollView) v.findViewById(R.id.s_day));
        parent.removeAllViews();
        Calendar c = Calendar.getInstance();
        c.setTime(displayDate);
        c.add(Calendar.DATE, 1);
        displayDate = c.getTime();
        dayForDate();
    }

    public void dayDown(View v1){
        ScrollView parent = ((ScrollView) v.findViewById(R.id.s_day));
        parent.removeAllViews();
        Calendar c = Calendar.getInstance();
        c.setTime(displayDate);
        c.add(Calendar.DATE, -1);
        displayDate = c.getTime();
        dayForDate();
    }

    public void dayUp(){
        ScrollView parent = ((ScrollView) v.findViewById(R.id.s_day));
        parent.removeAllViews();
        Calendar c = Calendar.getInstance();
        c.setTime(displayDate);
        c.add(Calendar.DATE, 1);
        displayDate = c.getTime();
        dayForDate();
    }

    public void dayDown(){
        ScrollView parent = ((ScrollView) v.findViewById(R.id.s_day));
        parent.removeAllViews();
        Calendar c = Calendar.getInstance();
        c.setTime(displayDate);
        c.add(Calendar.DATE, -1);
        displayDate = c.getTime();
        dayForDate();
    }

    private void dayForDate(){
        timetable=(ArrayList<urnikSchema>) app.getData().getList().clone();
        ScrollView parent = ((ScrollView) v.findViewById(R.id.s_day));
        parent.setOnTouchListener(new OnSwipeListener(app) {
            public void onSwipeRight() {
                dayDown();
            }
            public void onSwipeLeft() {
                dayUp();
            }
        });
        Iterator<urnikSchema> iter = timetable.iterator();
        while (iter.hasNext()) {
            urnikSchema p = iter.next();
            if (!MyData.dateToStringDate(p.getZacetek()).equals(MyData.dateToStringDate(displayDate))) iter.remove();
        }
        Collections.sort(timetable);
        DayAdapter.fillDay(timetable, v.getContext(), parent);
    }
}
