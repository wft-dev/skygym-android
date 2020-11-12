package com.wft.sky_gym.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wft.sky_gym.Adapters.NewMemberAdapter;
import com.wft.sky_gym.Admin.AddMember;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.R;

import java.util.ArrayList;

public class Memberfragment extends Fragment {
    RecyclerView recyclerView;
    RelativeLayout filterdrop;
    NewMemberAdapter adapter;
    ArrayList<MemberHelper> memberHelperList=new ArrayList<>();
    DatabaseReference databaseReference;
LinearLayout layout;
View view;
    ImageView add,filter;
    Activity context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_member,container,false);
        init(view);
        context=getActivity();
        setData();
        return view;
    }

    public void init(View view){
        add=view.findViewById(R.id.add);
        filter=view.findViewById(R.id.filter);
        filterdrop=view.findViewById(R.id.filterdrop);
layout=view.findViewById(R.id.layout);
      recyclerView= view.findViewById(R.id.memberlistrecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        FirebaseRecyclerOptions<MemberHelper> options =
//                new FirebaseRecyclerOptions.Builder<MemberHelper>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference("Member"), MemberHelper.class)
//                        .build();
//      //  Log.e("options",options.getSnapshots().getSnapshot(0).toString());
//        memberListAdapter = new MemberListAdapter(options);

     //   recyclerView.setAdapter(memberListAdapter);
//        databaseReference= FirebaseDatabase.getInstance().getReference("Member");
//
//        memberListAdapter = new MemberListAdapter(getActivity());
//        recyclerView.setAdapter(memberListAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, AddMember.class);
                startActivity(i);

            }
        });
filter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        filterdrop.setVisibility(View.VISIBLE);


    }
});
layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        hideKeyboard();

    }
});


    }
//    @Override
//    public void onStart(){
//        super.onStart();
//        memberListAdapter.startListening();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        memberListAdapter.stopListening();
//    }
    public void setData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MemberHelper data= dataSnapshot.getValue(MemberHelper.class);

                    memberHelperList.add(data);


                }
                adapter=new NewMemberAdapter(getActivity(),memberHelperList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.v("errr",error.getMessage());
            }
        });

    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}


