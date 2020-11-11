package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wft.sky_gym.Admin.AddEvent;
import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.EventHelper;
import com.wft.sky_gym.Admin.MembershipHelper;
import com.wft.sky_gym.Admin.TrainerHelper;
import com.wft.sky_gym.R;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {
    Context context;
    ArrayList<EventHelper> eventHelperArrayList;

    public EventListAdapter(Context context, ArrayList<EventHelper> eventHelperArrayList) {
        this.context = context;
        this.eventHelperArrayList= eventHelperArrayList;
    }

    @NonNull
    @Override
    public EventListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlistrecycler, parent, false);
        EventListAdapter.MyViewHolder vh = new EventListAdapter.MyViewHolder(mView);
        return vh;


    }


    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder holder, final int position) {

        EventHelper model=eventHelperArrayList.get(position);
        if (model.getTitle()==null){
            holder.name.setText("N/A");
        }
        else {
            holder.name.setText(model.getTitle());

        }
        if (model.getDetail()==null){
            holder.description.setText("N/A");
        }
        else {

            holder.description.setText(model.getDetail());
        }
        if (model.  getSdate()==null){
            holder.stime.setText("N/A");
        }
        else {

            holder.stime.setText(model.getSdate());
        }
        if (model.  getEdate()==null){
            holder.etime.setText("N/A");
        }
        else {

            holder.etime.setText(model.getSdate());
        }
        if (model. getDate()==null){
            holder.date.setText("N/A");
        }
        else {

            holder.date.setText(model.getDate());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventHelper eventHelper = eventHelperArrayList.get(position);
                Intent intent = new Intent(context, AddEvent.class);
                intent.putExtra("title",eventHelper.getTitle());
                intent.putExtra("detail",eventHelper.getDetail());
                intent.putExtra("date",eventHelper.getDate());
                intent.putExtra("stime",eventHelper.getSdate());
                intent.putExtra("etime",eventHelper.getEdate());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return eventHelperArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,description,stime, etime,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
            stime=itemView.findViewById(R.id.stime);
            etime=itemView.findViewById(R.id.etime);
            date=itemView.findViewById(R.id.date);
        }
    }
}
