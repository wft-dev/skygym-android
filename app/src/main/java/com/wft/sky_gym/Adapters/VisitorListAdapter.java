package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.AddVisitor;
import com.wft.sky_gym.Admin.TrainerHelper;
import com.wft.sky_gym.Admin.VisitorHelper;
import com.wft.sky_gym.R;

import java.util.ArrayList;

public class VisitorListAdapter  extends RecyclerView.Adapter<VisitorListAdapter.MyViewHolder> {
    Context context;
    ArrayList<VisitorHelper> visitorHelperArrayList;

    public VisitorListAdapter(Context context, ArrayList<VisitorHelper> visitorHelperArrayList) {
        this.context = context;
        this.visitorHelperArrayList= visitorHelperArrayList;
    }

    @NonNull
    @Override
    public VisitorListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitorlistrecycler, parent, false);
        VisitorListAdapter.MyViewHolder vh = new VisitorListAdapter.MyViewHolder(mView);
        return vh;


    }


    @Override
    public void onBindViewHolder(@NonNull VisitorListAdapter.MyViewHolder holder, final int position) {

        VisitorHelper model=visitorHelperArrayList.get(position);
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
        if (model.getDov()==null){
            holder.dov.setText("N/A");
        }
        else {

            holder.dov.setText(model.getDov());
        }
//        if (model.get()==null){
//            holder.dov.setText("N/A");
//        }
//        else {
//
//            holder.dov.setText(model.getDov());
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisitorHelper visitorHelper = visitorHelperArrayList.get(position);
                Intent intent = new Intent(context, AddVisitor.class);
                intent.putExtra("fname",visitorHelper.getFname());
                intent.putExtra("lname",visitorHelper.getLname());

                intent.putExtra("email",visitorHelper.getEmail());
                intent.putExtra("contact",visitorHelper.getContact());
                intent.putExtra("dov",visitorHelper.getDov());
                intent.putExtra("doj",visitorHelper.getDoj());
                intent.putExtra("gender",visitorHelper.getGender());
                intent.putExtra("add",visitorHelper.getAddress());
                intent.putExtra("visits",visitorHelper.getVisits());
                intent.putExtra("address",visitorHelper.getAddress());

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return visitorHelperArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,contact,dov,doj,trainer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            contact=itemView.findViewById(R.id.contact);
            doj=itemView.findViewById(R.id.doj);
            dov=itemView.findViewById(R.id.dov);
            trainer=itemView.findViewById(R.id.trainer);


        }
    }
}
