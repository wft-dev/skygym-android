package com.wft.sky_gym.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.wft.sky_gym.Adapters.EventListAdapter;
import com.wft.sky_gym.Adapters.TrainerListAdapter;
import com.wft.sky_gym.Adapters.VisitorListAdapter;
import com.wft.sky_gym.Admin.AddTrainer;
import com.wft.sky_gym.Admin.AddVisitor;
import com.wft.sky_gym.Admin.EventHelper;
import com.wft.sky_gym.Admin.VisitorHelper;
import com.wft.sky_gym.R;

import java.util.ArrayList;

public class VisitorsFragment  extends Fragment {
    RecyclerView recyclerView;
    VisitorListAdapter adapter;
    ArrayList<VisitorHelper> visitorHelperArrayList=new ArrayList<>();

    ImageView add;
    Activity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visitors, container, false);
        init(view);
        context = getActivity();
        setData();
        return view;
    }

    public void init(View view) {
        recyclerView = view.findViewById(R.id.visitorlistrecycler);
        add = view.findViewById(R.id.add);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddVisitor.class);
                startActivity(i);

            }
        });
    }
    public void setData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Visitor");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    VisitorHelper data= dataSnapshot.getValue(VisitorHelper.class);

                    visitorHelperArrayList.add(data);


                }
                adapter=new VisitorListAdapter(getActivity(),visitorHelperArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.v("errr",error.getMessage());
            }
        });

    }
}



