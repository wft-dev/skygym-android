package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.wft.sky_gym.Admin.AddEvent;
import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.EventHelper;
import com.wft.sky_gym.Admin.MembershipHelper;
import com.wft.sky_gym.Admin.TrainerHelper;

import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

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
    public void onBindViewHolder(@NonNull final EventListAdapter.MyViewHolder holder, final int position) {

        final EventHelper model=eventHelperArrayList.get(position);
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
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event");
               final String Title = holder.name.getText().toString();
//                final String Detail = holder.description.getText().toString();
//                final String Date = holder.date.getText().toString();
//                final String Stime = holder.stime.getText().toString();
//                final String Etime = holder.etime.getText().toString();
//                EventHelper eventHelper = new EventHelper(Title,Date,Detail,Stime,Etime);
                reference.child(Title).removeValue();
                eventHelperArrayList.remove(position);
                Toast.makeText(context,"Data Deleted successfully",Toast.LENGTH_SHORT).show();

//                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//                Query db = ref.child("Event").child();
//                db.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot data:snapshot.getChildren()){
//                            data.getRef().removeValue();
//                            Toast.makeText(context,"data deleted successfully",Toast.LENGTH_SHORT).show();
//
//                        }
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(context,"error data can't be deleted",Toast.LENGTH_SHORT).show();
//
//                    }
//                });

            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
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
RelativeLayout layout;
ImageView delete;
        SharedPrefs sharedPrefs;
        DatabaseReference refrence;
        EventHelper data;
        FirebaseDatabase firebaseDatabase;
        TextView name,description,stime, etime,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            layout=itemView.findViewById(R.id.layout);
            description=itemView.findViewById(R.id.description);
            stime=itemView.findViewById(R.id.stime);
            etime=itemView.findViewById(R.id.etime);
            date=itemView.findViewById(R.id.date);
            delete = itemView.findViewById(R.id.delete);
            sharedPrefs = new SharedPrefs(context);
            data = sharedPrefs.getEventData();
            refrence = FirebaseDatabase.getInstance().getReference().child("Event");
        }
    }

}
