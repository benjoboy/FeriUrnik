package com.skupina1.urnik.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.skupina1.urnik.Map.MapActivity;
import com.skupina1.urnik.R;

import java.util.ArrayList;


class Pair {
    String name, value;

    Pair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + name + " " + value + ')';
    }
}


public class MapFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(com.skupina1.urnik.R.layout.maplist, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.rvData);
        ArrayList<Pair> names = initPairData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final PairAdapter adapter = new PairAdapter(getContext(), names);
        adapter.setClickListener(new PairAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), MapActivity.class);
                intent.putExtra("hallID", adapter.getItem(position).value);
                intent.putExtra("hallName", adapter.getItem(position).name);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        return v;
    }

    private ArrayList<Pair> initPairData() {
        return new ArrayList<Pair>() {{
            add(new Pair("A 3. nadstropje", "ic_a_n3"));
            add(new Pair("C", "ic_c"));
            add(new Pair("E in P", "ic_e_p"));
            add(new Pair("F in G klet", "ic_fg_k"));
            add(new Pair("F in G prtličje", "ic_fg_p"));
            add(new Pair("F in G 1. nadstropje", "ic_fg_n1"));
            add(new Pair("F in G 1. med etaža", "ic_fg_m1"));
            add(new Pair("F in G 2. nadstropje", "ic_fg_n2"));
            add(new Pair("F in G 2. med etaža", "ic_fg_m2"));
            add(new Pair("F in G medsarda", "ic_fg_man"));
            add(new Pair("G2 prtličje", "ic_g2_p"));
            add(new Pair("G2 1. nadstropje", "ic_g2_n1"));
            add(new Pair("G2 2. nadstropje", "ic_g2_n2"));
            add(new Pair("G2 3. nadstropje", "ic_g2_n3"));
            add(new Pair("G2 4. nadstropje", "ic_g2_n4"));
            add(new Pair("G3 klet", "ic_g3_k1"));
            add(new Pair("G3 prtlicje", "ic_g3_p1"));
            add(new Pair("G3 1. nadstopje", "ic_g3_m1"));
            add(new Pair("G3 2. nadstopje", "ic_g3_n1"));
        }};
    }
}
