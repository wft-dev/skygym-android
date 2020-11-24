package com.wft.sky_gym.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;
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
RecyclerViewClickListener mrecyclerviewclicklistner;
    public NewMemberAdapter(Context context, ArrayList<MemberHelper> memberHelperList,RecyclerViewClickListener mrecyclerviewclicklistner) {
        this.context = context;
        this.memberHelperList= memberHelperList;
        this.mrecyclerviewclicklistner= mrecyclerviewclicklistner;
    }




    @NonNull
    @Override
    public NewMemberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberlistrecycler, parent, false);
        NewMemberAdapter.MyViewHolder vh = new NewMemberAdapter.MyViewHolder(mView,mrecyclerviewclicklistner);
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
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"click performed",Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        });
        holder.attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, MemberAttendance.class);
                context.startActivity(i);
            }
        });
holder.call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0377778888"));

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
       context.startActivity(callIntent);
    }
});


    }

//    private void onCall() {
//        Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
//        callIntent.setData(Uri.parse(""));    //this is the phone number calling
//        //check permission
//        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
//        //the system asks the user to grant approval.
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            //request permission from user if the app hasn't got the required permission
//            ActivityCompat.requestPermissions(
//                    ,
//                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
//                    10);
//            return;
//        }else {     //have got permission
//            try{
//                context.startActivity(callIntent);  //call activity and make phone call
//            }
//            catch (android.content.ActivityNotFoundException ex){
//                Toast.makeText(context,"yourActivity is not founded",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    @Override
    public int getItemCount() {
        return memberHelperList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView attendance,call;
        TextView memname,contact;

RecyclerViewClickListener recyclerViewClickListener;

        public MyViewHolder(@NonNull View itemView,RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            memname=itemView.findViewById(R.id.memname);
            contact=itemView.findViewById(R.id.contact);
            attendance=itemView.findViewById(R.id.attendance);
            this.recyclerViewClickListener=recyclerViewClickListener;
call= itemView.findViewById(R.id.call);
itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
     recyclerViewClickListener.recyclerViewListClicked(getAdapterPosition());
//     MemberHelper memberHelper = memberHelperList.get(getAdapterPosition());
//     Intent intent = new Intent(context, AddMember.class);
//     intent.putExtra("membername",memberHelper.getMemname());
//     intent.putExtra("memberid",memberHelper.getMemid());
//     intent.putExtra("dateofjoin",memberHelper.getDoj());
//     intent.putExtra("dob",memberHelper.getDob());
//     intent.putExtra("gender",memberHelper.getGender());
//     intent.putExtra("pass",memberHelper.getPassword());
//     intent.putExtra("email",memberHelper.getEmail());
//     intent.putExtra("tname",memberHelper.getTname());
//     intent.putExtra("address",memberHelper.getAddress());
//     intent.putExtra("contact",memberHelper.getContact());
//
//            context.startActivity(intent);
        }
    }
    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(int position);
    }
}
