package com.skupina1.urnik.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libdata.MyData;
import com.example.libdata.urnikSchema;
import com.skupina1.urnik.AdapterTasks;
import com.skupina1.urnik.ApplicationMy;
import com.skupina1.urnik.DayAdapter;
import com.skupina1.urnik.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class AgendaFragment extends Fragment {

    RecyclerView rvTask;
    AdapterTasks adapter;
    ApplicationMy app;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_agenda, container, false);

        rvTask = (RecyclerView) v.findViewById(R.id.rvTasks);
        app = (ApplicationMy) getActivity().getApplication();
        adapter = new AdapterTasks(app.getData().getList());
        rvTask.setAdapter(adapter);
        rvTask.setLayoutManager(new LinearLayoutManager(getContext()));
        final Context con = getContext();
        adapter.setOnItemClickListener(new AdapterTasks.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //Toast toast=Toast.makeText(getActivity().getApplicationContext(),"Klik " + position,Toast.LENGTH_SHORT);
                //toast.show();
                urnikSchema task = adapter.getMyList().get(position);
                DayAdapter.makePopup(task, con, itemView);
            }
        });


        return v;
    }

}
