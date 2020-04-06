package com.skupina1.urnik.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skupina1.urnik.Classes.Professor;
import com.skupina1.urnik.Classes.TalkingHours;
import com.skupina1.urnik.R;
import com.skupina1.urnik.ProfessorRecyclerViewAdapter;

import java.util.ArrayList;

public class ProfessorsFragment extends Fragment {

    //vars
    private ArrayList<Professor> professors;
    private RecyclerView recyclerView;
    private ProfessorRecyclerViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_professors, container, false);


        professors = new ArrayList<Professor>();
        recyclerView = v.findViewById(R.id.recyclerViewIzvajatelji);
        GenerateProfessors();
        initRecyclerView();

        return v;
    }



    private void GenerateProfessors(){
        professors.add(new Professor("Peter", "Kokol", "GAMA", new TalkingHours("12:00", "14:00", "Četrtek")));
        professors.add(new Professor("Damjan", "Strnad", "GAMA", new TalkingHours("12:00", "14:00", "Četrtek")));
        professors.add(new Professor("Luka", "Lukač", "GAMA", new TalkingHours("12:00", "14:00", "Četrtek")));
    }

    private void initRecyclerView(){
        adapter = new ProfessorRecyclerViewAdapter(professors, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

}
