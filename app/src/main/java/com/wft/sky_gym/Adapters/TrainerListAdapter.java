package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wft.sky_gym.Admin.AddMember;
import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.EventHelper;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.Admin.TrainerHelper;
import com.wft.sky_gym.R;

import java.util.ArrayList;

public class TrainerListAdapter extends RecyclerView.Adapter<TrainerListAdapter.MyViewHolder> {
    Context context;
    ArrayList<TrainerHelper> trainerHelperArrayList;

    public TrainerListAdapter(Context context, ArrayList<TrainerHelper> trainerHelperArrayList) {
        this.context = context;
        this.trainerHelperArrayList= trainerHelperArrayList;
    }

    @NonNull
    @Override
    public TrainerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainerlistrecycler, parent, false);
        TrainerListAdapter.MyViewHolder vh = new TrainerListAdapter.MyViewHolder(mView);
        return vh;


    }


    @Override
    public void onBindViewHolder(@NonNull TrainerListAdapter.MyViewHolder holder, final int position) {

        TrainerHelper model=trainerHelperArrayList.get(position);
        if (model.getFname()==null){
            holder.name.setText("N/A");
        }
        else {
            holder.name.setText(model.getFname());

        }
        if (model.getContact()==null){
            holder.contact.setText("N/A");
        }
        else {

            holder.contact.setText(model.getContact());
        }
        if (model.  getDoj()==null){
            holder.doj.setText("N/A");
        }
        else {

            holder.doj.setText(model.getDoj());
        }
        if (model.  getSalary()==null){
            holder.salary.setText("N/A");
        }
        else {

            holder.salary.setText(model.getSalary());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainerHelper trainerHelper = trainerHelperArrayList.get(position);
                Intent intent = new Intent(context, AddTrainer.class);
                intent.putExtra("fname",trainerHelper.getFname());
                intent.putExtra("lname",trainerHelper.getLname());
                intent.putExtra("tid",trainerHelper.getTid());
                intent.putExtra("email",trainerHelper.getEmail());
                intent.putExtra("contact",trainerHelper.getContact());
                intent.putExtra("dob",trainerHelper.getDob());
                intent.putExtra("doj",trainerHelper.getDoj());
                intent.putExtra("gender",trainerHelper.getGender());
                intent.putExtra("salary",trainerHelper.getSalary());
                intent.putExtra("shiftdays",trainerHelper.getShiftdays());
                intent.putExtra("shifttimings",trainerHelper.getShifttimings());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return trainerHelperArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,contact,doj,salary;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            contact=itemView.findViewById(R.id.contact);
            doj=itemView.findViewById(R.id.doj);
            salary=itemView.findViewById(R.id.salary);


        }
    }
}
