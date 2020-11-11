package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wft.sky_gym.Admin.AddMember;
import com.wft.sky_gym.Admin.LoginAdmin;
import com.wft.sky_gym.Admin.MemberAttendance;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.Admin.UsersHelperClass;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.ArrayList;

public class NewMemberAdapter extends RecyclerView.Adapter<NewMemberAdapter.MyViewHolder> {
    Context context;
    ArrayList<MemberHelper> memberHelperList;

    public NewMemberAdapter(Context context, ArrayList<MemberHelper> memberHelperList) {
        this.context = context;
        this.memberHelperList= memberHelperList;
    }

    @NonNull
    @Override
    public NewMemberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberlistrecycler, parent, false);
        NewMemberAdapter.MyViewHolder vh = new NewMemberAdapter.MyViewHolder(mView);
        return vh;


    }


    @Override
    public void onBindViewHolder(@NonNull NewMemberAdapter.MyViewHolder holder, final int position) {

        MemberHelper model=memberHelperList.get(position);
        if (model.getMemname()==null){
            holder.memname.setText("N/A");
        }
        else {
            holder.memname.setText(model.getMemname());

        }
        if (model.getContact()==null){
            holder.contact.setText("N/A");
        }
        else {

            holder.contact.setText(model.getContact());
        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                MemberHelper member= memberHelperList.get(position);
////                SharedPrefs sharedPreferences = new SharedPrefs(context);
////                sharedPreferences.createMemberDataSession(member);
//                Intent i=new Intent(context, AddMember.class);
//                context.startActivity(i);
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberHelper memberHelper = memberHelperList.get(position);
                Intent intent = new Intent(context, AddMember.class);
                intent.putExtra("membername",memberHelper.getMemname());
                intent.putExtra("memberid",memberHelper.getMemid());
                intent.putExtra("dateofjoin",memberHelper.getDoj());
                intent.putExtra("dob",memberHelper.getDob());
                intent.putExtra("gender",memberHelper.getGender());
                intent.putExtra("pass",memberHelper.getPassword());
                intent.putExtra("email",memberHelper.getEmail());
                intent.putExtra("tname",memberHelper.getTname());
                intent.putExtra("address",memberHelper.getAddress());
                intent.putExtra("contact",memberHelper.getContact());

                context.startActivity(intent);

            }
        });
        holder.attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, MemberAttendance.class);
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return memberHelperList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
ImageView attendance;
        TextView memname,contact;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            memname=itemView.findViewById(R.id.memname);
            contact=itemView.findViewById(R.id.contact);
            attendance=itemView.findViewById(R.id.attendance);


        }

    }
}
