package com.skupina1.urnik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libdata.MyData;
import com.example.libdata.urnikSchema;

import java.util.ArrayList;
import java.util.Date;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.ViewHolder> {
    ArrayList<urnikSchema> myList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AdapterTasks(ArrayList<urnikSchema> myList) {
        super();
        this.myList = myList;
    }

    public ArrayList<urnikSchema> getMyList() {
        return myList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtAssistant;
        public TextView txtTime;
        public TextView txtDate;
        public TextView txtLocation;
        public ConstraintLayout.LayoutParams params;
        public ConstraintLayout rootView;

        public ViewHolder(View v) {
            super(v);
            rootView = v.findViewById(R.id.rv_agenda_layout);
            params = new ConstraintLayout.LayoutParams(0, 0);
            txtName = (TextView) v.findViewById(R.id.nameDisplay);
            txtAssistant = (TextView) v.findViewById(R.id.typeDisplay);
            txtTime= (TextView) v.findViewById(R.id.timeDisplay);
            txtDate= (TextView) v.findViewById(R.id.dateDisplay);
            txtLocation= (TextView) v.findViewById(R.id.locationDisplay);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public AdapterTasks.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.rv_agenda_row, parent, false);
        // Return a new holder instance
        AdapterTasks.ViewHolder viewHolder = new AdapterTasks.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterTasks.ViewHolder holder, int position) {
        String lastDate="";
        String lastTime="";
        if(position!=0){
            final urnikSchema last = myList.get(position-1);
            lastDate=MyData.dateToStringDate(last.getZacetek());
            lastTime=MyData.dateToStringTime(last.getZacetek());
        }
        final urnikSchema current = myList.get(position);

        if(current.getZacetek().compareTo(new Date(System.currentTimeMillis()))<0){
            holder.rootView.setLayoutParams(holder.params);
            return;
        }

        holder.txtName.setText(current.getPredmet().getNaziv());
        holder.txtAssistant.setText(current.getTip());
        holder.txtLocation.setText(current.getProstor().getNaslov());
        if(!lastDate.equals(MyData.dateToStringDate(current.getZacetek()))){
            holder.txtDate.setText(MyData.dateToStringDate(current.getZacetek()));
        }else {
            holder.txtDate.setVisibility(View.GONE);
        }
        if(!lastTime.equals(MyData.dateToStringTime(current.getZacetek()))||!lastDate.equals(MyData.dateToStringDate(current.getZacetek()))){
            holder.txtTime.setText(MyData.dateToStringTime(current.getZacetek()));
        }else {

            holder.txtTime.setVisibility(View.GONE);

        }
    }
    @Override
    public int getItemCount() {
        return myList.size();
    }
}

