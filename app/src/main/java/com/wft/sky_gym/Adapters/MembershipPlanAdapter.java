package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.MemberAttendance;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.Admin.MembershipHelper;
import com.wft.sky_gym.Admin.TrainerHelper;
import com.wft.sky_gym.Admin.ViewMembership;
import com.wft.sky_gym.R;

import java.util.ArrayList;

public class MembershipPlanAdapter extends RecyclerView.Adapter<MembershipPlanAdapter.MyViewHolder> {
    Context context;
    ArrayList<MembershipHelper> membershipHelperArrayList;

    public MembershipPlanAdapter(Context context, ArrayList<MembershipHelper> membershipHelperArrayList) {
        this.context = context;
        this.membershipHelperArrayList= membershipHelperArrayList;
    }

    @NonNull
    @Override
    public MembershipPlanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.membershipplanrecycler, parent, false);
        MembershipPlanAdapter.MyViewHolder vh = new MembershipPlanAdapter.MyViewHolder(mView);
        return vh;


    }


    @Override
    public void onBindViewHolder(@NonNull final MembershipPlanAdapter.MyViewHolder holder, final int position) {

        MembershipHelper model = membershipHelperArrayList.get(position);
        if (model.getTitle() == null) {
            holder.name.setText("N/A");
        } else {
            holder.name.setText(model.getTitle());

        }
        if (model.getDescription() == null) {
            holder.description.setText("N/A");
        } else {

            holder.description.setText(model.getDescription());
        }
        if (model.getSdate() == null) {
            holder.date1.setText("N/A");
        } else {

            holder.date1.setText(model.getSdate());
        }
        if (model.getEdate() == null) {
            holder.date2.setText("N/A");
        } else {

            holder.date2.setText(model.getSdate());
        }
        if (model.getAmount() == null) {
            holder.price.setText("N/A");
        } else {

            holder.price.setText(model.getAmount());
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MembershipPlan");
                                                 final String Title = holder.name.getText().toString();
//                final String Detail = holder.description.getText().toString();
//                final String Date = holder.date.getText().toString();
//                final String Stime = holder.stime.getText().toString();
//                final String Etime = holder.etime.getText().toString();
//                EventHelper eventHelper = new EventHelper(Title,Date,Detail,Stime,Etime);
                                                 reference.child(Title).removeValue();
                                                 membershipHelperArrayList.remove(position);
                                                 Toast.makeText(context, "Data Deleted successfully", Toast.LENGTH_SHORT).show();
                                                 notifyDataSetChanged();
                                             }
                                         });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MembershipHelper membershipHelper = membershipHelperArrayList.get(position);
                Intent intent = new Intent(context, ViewMembership.class);
                intent.putExtra("title",membershipHelper.getTitle());
                intent.putExtra("detail",membershipHelper.getDescription());
                intent.putExtra("amount",membershipHelper.getAmount());
                intent.putExtra("sdate",membershipHelper.getSdate());
                intent.putExtra("edate",membershipHelper.getEdate());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return membershipHelperArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
RelativeLayout layout;
ImageView delete;
        TextView name,description,date1, date2,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            layout=itemView.findViewById(R.id.layout);
            description=itemView.findViewById(R.id.description);
            date1=itemView.findViewById(R.id.date1);
            date2=itemView.findViewById(R.id.date2);
            price=itemView.findViewById(R.id.price);
            delete=itemView.findViewById(R.id.delete);


        }
    }
}
