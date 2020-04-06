package com.skupina1.urnik;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.skupina1.urnik.Classes.Professor;
import com.skupina1.urnik.Classes.TalkingHours;

import java.util.ArrayList;
import java.util.List;

public class ProfessorRecyclerViewAdapter extends RecyclerView.Adapter<ProfessorRecyclerViewAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Professor> professors;
    private ArrayList<Professor> professorsFull;
    private Context mContext;

    public ProfessorRecyclerViewAdapter(ArrayList<Professor> professors, Context mContext) {
        this.professors = professors;
        this.mContext = mContext;
        professorsFull = new ArrayList<Professor>(professors);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_professor_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:  called");

        Professor p = professors.get(position);
        TalkingHours h = p.getTalkingHours();
        holder.name.setText(p.getName() + " " + p.getSurname());
        holder.workspace.setText(p.getWorkSpace());
        holder.time.setText(h.getDay() + ": " + h.getStart() + " : " + h.getEnd());

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + position);
                //on item click
            }
        });
    }

    @Override
    public int getItemCount() {
        return professors.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Professor> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(professorsFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Professor item : professorsFull){
                    if(item.getName().toLowerCase().contains(filterPattern) || item.getSurname().contains(filterPattern))
                        filteredList.add(item);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            professors.clear();
            professors.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView workspace;
        TextView time;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View v) {
            super(v);
            //image = v.findViewById(R.id.image);
            name = v.findViewById(R.id.textViewName);
            workspace = v.findViewById(R.id.textViewWorkSpace);
            time = v.findViewById(R.id.textViewTime);
            parentLayout = v.findViewById(R.id.parentLayout);
        }
    }

}
