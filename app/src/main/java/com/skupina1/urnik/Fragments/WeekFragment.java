package com.skupina1.urnik.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libdata.MyData;
import com.example.libdata.urnikSchema;
import com.skupina1.urnik.ApplicationMy;
import com.skupina1.urnik.DayAdapter;
import com.skupina1.urnik.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class WeekFragment extends Fragment {
    ApplicationMy app;
    ArrayList<urnikSchema> timetable;
    Date displayDate;
    Date monday;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.activity_week, container, false);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
        monday = cal.getTime();
        displayDate = monday;

        dayUp();
        dayUp();
        dayUp();
        dayUp();
        dayUp();

        return v;
    }

    public void weekUp(View v1){
        ((LinearLayout)v.findViewById(R.id.lin_lay)).removeAllViews();
        Calendar c = Calendar.getInstance();
        c.setTime(monday);
        c.add(Calendar.DATE, 7);
        monday = c.getTime();
        displayDate = monday;

        dayUp();
        dayUp();
        dayUp();
        dayUp();
        dayUp();
    }

    public void weekDown(View v1){
        ((LinearLayout)v.findViewById(R.id.lin_lay)).removeAllViews();
        Calendar c = Calendar.getInstance();
        c.setTime(monday);
        c.add(Calendar.DATE, -7);
        monday = c.getTime();
        displayDate = monday;

        dayUp();
        dayUp();
        dayUp();
        dayUp();
    }

    public void dayUp(){
        Calendar c = Calendar.getInstance();
        c.setTime(displayDate);
        c.add(Calendar.DATE, 1);
        displayDate = c.getTime();
        dayForDate();
    }

    public void dayDown(){
        Calendar c = Calendar.getInstance();
        c.setTime(displayDate);
        c.add(Calendar.DATE, -1);
        displayDate = c.getTime();
        dayForDate();
    }

    private void dayForDate(){
        app = (ApplicationMy) getActivity().getApplication();
        timetable=(ArrayList<urnikSchema>) app.getData().getList().clone();
        LinearLayout lin = (LinearLayout)v.findViewById(R.id.lin_lay);
        Iterator<urnikSchema> iter = timetable.iterator();
        while (iter.hasNext()) {
            urnikSchema p = iter.next();
            if (!MyData.dateToStringDate(p.getZacetek()).equals(MyData.dateToStringDate(displayDate))) iter.remove();
        }
        Collections.sort(timetable);
        if(timetable.size()==0){
            return;
        }
        DayAdapter.fillDay(timetable, v.getContext(), lin);
    }
}
