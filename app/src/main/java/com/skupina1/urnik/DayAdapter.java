package com.skupina1.urnik;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.example.libdata.MyData;
import com.example.libdata.urnikSchema;

import java.util.ArrayList;
import java.util.Iterator;

public class DayAdapter {

    public static ConstraintLayout fillDay(final ArrayList<urnikSchema> urnik, final Context con, final ScrollView outside){
        ArrayList<ArrayList<Integer>> dayList = getDiffHours(urnik, false);
        ArrayList<Integer> max= new ArrayList<Integer>();
        ArrayList<String> hours = new ArrayList<>();

        final ArrayList<ImageView> squares = new ArrayList<ImageView>();
        ArrayList<View> heights = new ArrayList<View>();
        ConstraintLayout parentLayout = new ConstraintLayout(con);
        parentLayout.setId(View.generateViewId());
        ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        ConstraintSet set = new ConstraintSet();
        outside.addView(parentLayout);

        parentLayout.setPadding(5,0, 5, 25);

        /*set.clone(outside);
        set.connect(parentLayout.getId(), ConstraintSet.TOP, outside.getId(), ConstraintSet.TOP);
        set.connect(parentLayout.getId(), ConstraintSet.START, outside.getId(), ConstraintSet.START);
        set.applyTo(outside);*/

        for(int i=0; i<urnik.size(); i++){
            ImageView temp = new ImageView(con);

            temp.setId(View.generateViewId());
            //temp.setTag(i);
            final int j = i;
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DayAdapter.makePopup(urnik.get(j), con, outside);
                }
            });

            squares.add(temp);
            max.add(0);
        }

        for(int i=0; i<dayList.size(); i++){

            String timeText = stringFromIndex(dayList.get(i).get(dayList.get(i).size()-1));
            dayList.get(i).remove(dayList.get(i).size()-1);

            View temp = new View(con);
            View line = new View(con);
            TextView text = new TextView(con);
            temp.setId(View.generateViewId());
            line.setId(View.generateViewId());
            text.setId(View.generateViewId());
            parentLayout.addView(temp);
            parentLayout.addView(text);
            parentLayout.addView(line);

            param = new ConstraintLayout.LayoutParams(120, 400);
             if(i==0){
                 param.setMargins(0,60,0,0);
             }else{
                 param.setMargins(0,3,0,0);
             }

            temp.setLayoutParams(param);

            param = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3);
            line.setLayoutParams(param);
            line.setBackgroundColor(Color.parseColor("#999999"));

            text.setTextColor(Color.parseColor("#999999"));
            text.setText(timeText);
            text.setTextSize(13);
            text.setTypeface(null, Typeface.BOLD);


            set.clone(parentLayout);

            if(i==0){
                set.connect(temp.getId(), ConstraintSet.TOP, parentLayout.getId(), ConstraintSet.TOP);
            }else{
                set.connect(temp.getId(), ConstraintSet.TOP, heights.get(i-1).getId(), ConstraintSet.BOTTOM);
            }
            set.connect(line.getId(), ConstraintSet.TOP, temp.getId(), ConstraintSet.TOP);
            set.connect(text.getId(), ConstraintSet.BOTTOM, temp.getId(), ConstraintSet.TOP);

            set.applyTo(parentLayout);

            heights.add(temp);
        }
        Log.d("DayAdapter", " "+dayList.toString());

        for(int i=0; i<dayList.size(); i++){
            for(int j=0; j<dayList.get(i).size(); j++){
                if(i>0){
                    if(dayList.get(i-1).contains(dayList.get(i).get(j))){
                        continue;
                    }
                }

                ImageView temp = squares.get(dayList.get(i).get(j));
                parentLayout.addView(temp);
                param = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
                param.setMargins(5,10,5,5);
                temp.setLayoutParams(param);

                set.clone(parentLayout);

                if(j==0){
                    set.connect(temp.getId(), ConstraintSet.START, heights.get(i).getId(), ConstraintSet.END);
                }else{
                    set.connect(temp.getId(), ConstraintSet.START, squares.get(dayList.get(i).get(j-1)).getId(), ConstraintSet.END);
                    if(max.get(dayList.get(i).get(j-1))==0){
                        set.connect(squares.get(dayList.get(i).get(j-1)).getId(), ConstraintSet.END, temp.getId(), ConstraintSet.START);;
                        max.set(dayList.get(i).get(j-1), 1);
                    }
                }
                if(j==dayList.get(i).size()-1){
                    set.connect(temp.getId(), ConstraintSet.END, parentLayout.getId(), ConstraintSet.END);
                    if(i!=dayList.size()-1){
                        if(dayList.get(i+1).contains(dayList.get(i).get(j))){
                            int index1 = dayList.get(i+1).indexOf(dayList.get(i).get(j));
                            if(index1!=dayList.get(i+1).size()-1){
                                set.connect(temp.getId(), ConstraintSet.END, squares.get(dayList.get(i+1).get(index1+1)).getId(), ConstraintSet.START);
                            }
                        }
                    }
                }else{
                    set.connect(temp.getId(), ConstraintSet.END, squares.get(dayList.get(i).get(j+1)).getId(), ConstraintSet.START);
                }
                set.connect(temp.getId(), ConstraintSet.TOP, heights.get(i).getId(), ConstraintSet.TOP);

                int iRange=0;
                if(i!=dayList.size()-1){
                    int index=indexFromString(MyData.dateToStringTime(urnik.get(dayList.get(i).get(j)).getZacetek()));
                    int index2=indexFromString(MyData.dateToStringTime(urnik.get(dayList.get(i).get(j)).getKonec()));
                    iRange=index2-index-1;
                }
                Log.d("AAA", ""+dayList.get(i).get(j)+ " " + iRange);
                set.connect(temp.getId(), ConstraintSet.BOTTOM, heights.get(i+iRange).getId(), ConstraintSet.BOTTOM);

                set.applyTo(parentLayout);

                temp.setBackground(ContextCompat.getDrawable(con, R.drawable.rounded_item));

                TextView t1 = new TextView(con);
                TextView t2 = new TextView(con);
                TextView t3 = new TextView(con);
                t1.setId(View.generateViewId());
                t2.setId(View.generateViewId());
                t3.setId(View.generateViewId());
                parentLayout.addView(t1);
                parentLayout.addView(t2);
                parentLayout.addView(t3);

                param= new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
                t1.setLayoutParams(param);
                param= new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
                t2.setLayoutParams(param);
                param= new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT);
                t3.setLayoutParams(param);

                t1.setGravity(Gravity.CENTER);
                t2.setGravity(Gravity.CENTER);
                t3.setGravity(Gravity.CENTER);

                urnikSchema tempUrn = urnik.get(dayList.get(i).get(j));

                t1.setText(tempUrn.getPredmet().getNaziv());
                t2.setText(tempUrn.getTip());
                t3.setText(tempUrn.getProstor().getNaslov());
                t1.setTextColor(ContextCompat.getColor(con, R.color.colorOffWhite));
                t2.setTextColor(ContextCompat.getColor(con, R.color.colorOffWhite));
                t3.setTextColor(ContextCompat.getColor(con, R.color.colorOffWhite));
                t1.setTypeface(null, Typeface.BOLD);

                set.clone(parentLayout);
                set.connect(t2.getId(), ConstraintSet.TOP, temp.getId(), ConstraintSet.TOP);
                set.connect(t2.getId(), ConstraintSet.BOTTOM, temp.getId(), ConstraintSet.BOTTOM);
                set.connect(t1.getId(), ConstraintSet.BOTTOM, t2.getId(), ConstraintSet.TOP);
                set.connect(t3.getId(), ConstraintSet.TOP, t2.getId(), ConstraintSet.BOTTOM);
                set.connect(t2.getId(), ConstraintSet.START, temp.getId(), ConstraintSet.START);
                set.connect(t2.getId(), ConstraintSet.END, temp.getId(), ConstraintSet.END);
                set.connect(t1.getId(), ConstraintSet.START, temp.getId(), ConstraintSet.START);
                set.connect(t1.getId(), ConstraintSet.END, temp.getId(), ConstraintSet.END);
                set.connect(t3.getId(), ConstraintSet.START, temp.getId(), ConstraintSet.START);
                set.connect(t3.getId(), ConstraintSet.END, temp.getId(), ConstraintSet.END);
                set.applyTo(parentLayout);

            }

            //temp.getLayoutParams().width=0;
            //temp.getLayoutParams().height=20;
        }

        return (parentLayout);
    }

    public static ConstraintLayout fillDay(final ArrayList<urnikSchema> urnik, final Context con, final LinearLayout outside){
        ArrayList<ArrayList<Integer>> dayList = getDiffHours(urnik, true);
        float maxsize=0;
        ArrayList<Integer> max = new ArrayList<Integer>();
        ArrayList<String> hours = new ArrayList<>();
        Boolean empty;
        if(outside.getChildCount()>0){
            empty=false;
        }else{
            empty=true;
        }

        for(int i = 0; i<dayList.size(); i++){
            if(dayList.get(i).size()>maxsize){
                maxsize=dayList.get(i).size();
            }
        }

        final ArrayList<ImageView> squares = new ArrayList<ImageView>();
        ArrayList<View> heights = new ArrayList<View>();
        ConstraintLayout parentLayout = new ConstraintLayout(con);
        parentLayout.setId(View.generateViewId());
        ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams paramL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);


        ConstraintSet set = new ConstraintSet();
        outside.addView(parentLayout);
        parentLayout.setLayoutParams(paramL);

        if(empty){
            parentLayout.setPadding(5,0, 5, 25);
        }else{
            parentLayout.setPadding(1,0, 1, 25);
        }


        View lineEnd = new View(con);
        lineEnd.setId(View.generateViewId());
        parentLayout.addView(lineEnd);

        param = new ConstraintLayout.LayoutParams(2, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        lineEnd.setLayoutParams(param);
        lineEnd.setBackgroundColor(Color.parseColor("#999999"));

        set.clone(parentLayout);
        set.connect(lineEnd.getId(), ConstraintSet.TOP, parentLayout.getId(), ConstraintSet.TOP);
        set.connect(lineEnd.getId(), ConstraintSet.BOTTOM, parentLayout.getId(), ConstraintSet.BOTTOM);
        set.connect(lineEnd.getId(), ConstraintSet.END, parentLayout.getId(), ConstraintSet.END);
        set.applyTo(parentLayout);




        /*set.clone(outside);
        set.connect(parentLayout.getId(), ConstraintSet.TOP, outside.getId(), ConstraintSet.TOP);
        set.connect(parentLayout.getId(), ConstraintSet.START, outside.getId(), ConstraintSet.START);
        set.applyTo(outside);*/

        for(int i=0; i<urnik.size(); i++){
            ImageView temp = new ImageView(con);

            temp.setId(View.generateViewId());
            //temp.setTag(i);
            final int j = i;
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DayAdapter.makePopup(urnik.get(j), con, outside);
                }
            });

            squares.add(temp);
            max.add(0);
        }

        for(int i=0; i<dayList.size(); i++){

            String timeText = stringFromIndex(dayList.get(i).get(dayList.get(i).size()-1));
            dayList.get(i).remove(dayList.get(i).size()-1);

            View temp = new View(con);
            View line = new View(con);
            TextView text = new TextView(con);
            temp.setId(View.generateViewId());
            line.setId(View.generateViewId());
            text.setId(View.generateViewId());
            parentLayout.addView(temp);
            if(empty){
                parentLayout.addView(text);
            }
            parentLayout.addView(line);

            if(empty){
                param = new ConstraintLayout.LayoutParams(120, 150);
            }else{
                param = new ConstraintLayout.LayoutParams(1, 150);
            }

            if(i==0){
                param.setMargins(0,60,0,0);
            }else{
                param.setMargins(0,3,0,0);
            }

            temp.setLayoutParams(param);

            param = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3);
            line.setLayoutParams(param);
            line.setBackgroundColor(Color.parseColor("#999999"));

            text.setTextColor(Color.parseColor("#999999"));
            text.setText(timeText);
            text.setTextSize(13);
            text.setTypeface(null, Typeface.BOLD);


            set.clone(parentLayout);

            if(i==0){
                set.connect(temp.getId(), ConstraintSet.TOP, parentLayout.getId(), ConstraintSet.TOP);
            }else{
                set.connect(temp.getId(), ConstraintSet.TOP, heights.get(i-1).getId(), ConstraintSet.BOTTOM);
            }
            set.connect(line.getId(), ConstraintSet.TOP, temp.getId(), ConstraintSet.TOP);
            set.connect(text.getId(), ConstraintSet.BOTTOM, temp.getId(), ConstraintSet.TOP);

            set.applyTo(parentLayout);

            heights.add(temp);
        }
        Log.d("DayAdapter", " "+dayList.toString());
        TextView day = new TextView(con);
        day.setId(View.generateViewId());
        parentLayout.addView(day);
        day.setText(MyData.dateToStringDay(urnik.get(0).getZacetek()));
        day.setTextColor(Color.parseColor("#999999"));

        set.clone(parentLayout);
        set.connect(day.getId(), ConstraintSet.TOP, parentLayout.getId(), ConstraintSet.TOP);
        set.connect(day.getId(), ConstraintSet.START, heights.get(0).getId(), ConstraintSet.END);
        set.connect(day.getId(), ConstraintSet.END, parentLayout.getId(), ConstraintSet.END);
        set.applyTo(parentLayout);

        for(int i=0; i<dayList.size(); i++){
            for(int j=0; j<dayList.get(i).size(); j++){
                if(i>0){
                    if(dayList.get(i-1).contains(dayList.get(i).get(j))){
                        continue;
                    }
                }

                ImageView temp = squares.get(dayList.get(i).get(j));
                parentLayout.addView(temp);
                param = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
                param.setMargins(5,10,5,5);
                temp.setLayoutParams(param);

                set.clone(parentLayout);

                if(j==0){
                    set.connect(temp.getId(), ConstraintSet.START, heights.get(i).getId(), ConstraintSet.END);
                }else{
                    set.connect(temp.getId(), ConstraintSet.START, squares.get(dayList.get(i).get(j-1)).getId(), ConstraintSet.END);
                    if(max.get(dayList.get(i).get(j-1))==0){
                        set.connect(squares.get(dayList.get(i).get(j-1)).getId(), ConstraintSet.END, temp.getId(), ConstraintSet.START);;
                        max.set(dayList.get(i).get(j-1), 1);
                    }
                }
                if(j==dayList.get(i).size()-1){
                    set.connect(temp.getId(), ConstraintSet.END, parentLayout.getId(), ConstraintSet.END);
                    if(i!=dayList.size()-1){
                        if(dayList.get(i+1).contains(dayList.get(i).get(j))){
                            int index1 = dayList.get(i+1).indexOf(dayList.get(i).get(j));
                            if(index1!=dayList.get(i+1).size()-1){
                                set.connect(temp.getId(), ConstraintSet.END, squares.get(dayList.get(i+1).get(index1+1)).getId(), ConstraintSet.START);
                            }
                        }
                    }
                }else{
                    set.connect(temp.getId(), ConstraintSet.END, squares.get(dayList.get(i).get(j+1)).getId(), ConstraintSet.START);
                }
                set.connect(temp.getId(), ConstraintSet.TOP, heights.get(i).getId(), ConstraintSet.TOP);

                int iRange=0;
                if(i!=dayList.size()-1){
                    int index=indexFromString(MyData.dateToStringTime(urnik.get(dayList.get(i).get(j)).getZacetek()));
                    int index2=indexFromString(MyData.dateToStringTime(urnik.get(dayList.get(i).get(j)).getKonec()));
                    iRange=index2-index-1;
                }
                set.connect(temp.getId(), ConstraintSet.BOTTOM, heights.get(i+iRange).getId(), ConstraintSet.BOTTOM);

                set.applyTo(parentLayout);

                temp.setBackground(ContextCompat.getDrawable(con, R.drawable.rounded_item));

                TextView t1 = new TextView(con);
                t1.setId(View.generateViewId());
                parentLayout.addView(t1);

                urnikSchema tempUrn = urnik.get(dayList.get(i).get(j));

                t1.setText(tempUrn.getPredmet().getNaziv());
                t1.setTextColor(ContextCompat.getColor(con, R.color.colorOffWhite));
                t1.setTypeface(null, Typeface.BOLD);

                param= new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
                t1.setLayoutParams(param);
                t1.setGravity(Gravity.CENTER);

                set.clone(parentLayout);
                set.connect(t1.getId(), ConstraintSet.TOP, temp.getId(), ConstraintSet.TOP);
                set.connect(t1.getId(), ConstraintSet.BOTTOM, temp.getId(), ConstraintSet.BOTTOM);
                set.connect(t1.getId(), ConstraintSet.START, temp.getId(), ConstraintSet.START);
                set.connect(t1.getId(), ConstraintSet.END, temp.getId(), ConstraintSet.END);
                set.applyTo(parentLayout);

            }

            //temp.getLayoutParams().width=0;
            //temp.getLayoutParams().height=20;
        }

        return (parentLayout);
    }


    private static ArrayList<ArrayList<Integer>> getDiffHours(ArrayList<urnikSchema> urnik, Boolean week){
        ArrayList<ArrayList<Integer>> timetable = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<15; i++){
           timetable.add(new  ArrayList<Integer>());
        }
        for(int i=urnik.size()-1; i>=0; i--){
            int index=indexFromString(MyData.dateToStringTime(urnik.get(i).getZacetek()));
            int index2=indexFromString(MyData.dateToStringTime(urnik.get(i).getKonec()));
            timetable.get(index).add(i);
            /*for(int j=index+1; j<index2; j++){
                if(timetable.get(index).size()>timetable.get(j).size()){
                    timetable.get(j).add(i);
                    //Log.d("Bruh", "Pushing "+i);
                }else{
                    timetable.get(j).add(timetable.get(index).size()-1, i);
                    //Log.d("Bruh", "Slamming "+i + " to " + (timetable.get(index).size()-1));
                }
            }*/
        }

        for(int i=0; i<urnik.size(); i++){
            int index=indexFromString(MyData.dateToStringTime(urnik.get(i).getZacetek()));
            int index2=indexFromString(MyData.dateToStringTime(urnik.get(i).getKonec()));
            for(int j=index+1; j<index2; j++){
                if(timetable.get(index).indexOf(i)>timetable.get(j).size()){
                    timetable.get(j).add(i);
                    //Log.d("Bruh", "Pushing "+i);
                }else{
                    timetable.get(j).add(timetable.get(index).indexOf(i), i);
                    //Log.d("Bruh", "Slamming "+i + " to " + (timetable.get(index).size()-1));
                }
            }
        }
        Iterator<ArrayList<Integer>> iter = timetable.iterator();
        int iStevec1=0;
        while (iter.hasNext()) {
            ArrayList<Integer> p = iter.next();
            if (p.size()==0&&!week){
                iter.remove();
            }else{
                p.add(iStevec1);
            }
            iStevec1++;
        }
        return(timetable);
    }

    private static int indexFromString(String hour){
        switch (hour){
            case  "07:00":
                return 0;
            case  "08:00":
                return 1;
            case  "09:00":
                return 2;
            case "11:00":
                return 3;
            case "12:00":
                return 4;
            case "13:00":
                return 5;
            case "14:00":
                return 6;
            case "15:00":
                return 7;
            case "16:00":
                return 8;
            case "17:00":
                return 9;
            case "18:00":
                return 10;
            case "19:00":
                return 11;
            case "20:00":
                return 12;
            case "21:00":
                return 13;
            default:
                return 14;
        }
    }

    private static String stringFromIndex(int index){
        switch (index){
            case  0:
                return "07:00";
            case  1:
                return "08:00";
            case  2:
                return "09:00";
            case 3:
                return "11:00";
            case 4:
                return "12:00";
            case 5:
                return "13:00";
            case 6:
                return "14:00";
            case 7:
                return "15:00";
            case 8:
                return "16:00";
            case 9:
                return "17:00";
            case 10:
                return "18:00";
            case 11:
                return "19:00";
            case 12:
                return "20:00";
            case 13:
                return "21:00";
            default:
                return "...";
        }
    }

    public static void makePopup(urnikSchema task, Context con, View itemView){
        LayoutInflater inflater = (LayoutInflater)
                con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.info_popup, null);


        ((TextView) popupView.findViewById(R.id.nameDisplay)).setText(task.getPredmet().getNaziv());
        ((TextView) popupView.findViewById(R.id.assistantDisplay)).setText(task.getIzvajalec().getIme());
        ((TextView) popupView.findViewById(R.id.timeDisplay)).setText(MyData.dateToStringTime(task.getZacetek())+"-"+ MyData.dateToStringTime(task.getKonec()));
        ((TextView) popupView.findViewById(R.id.locationDisplay)).setText(task.getProstor().getNaslov());
        ((TextView) popupView.findViewById(R.id.typeDisplay)).setText(task.getTip());



        int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(itemView , Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
