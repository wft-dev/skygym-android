package com.wft.sky_gym.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

public class ViewMember  extends AppCompatActivity {
    SharedPrefs sharedPrefs;
    TextView name,contact, joindate,addmship,cmmdetail,purchase,attend;
    FirebaseAuth auth;

   MemberHelper data;
    DatabaseReference refrence;
    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.viewmember);
        sharedPrefs = new SharedPrefs(ViewMember.this);
name=findViewById(R.id.name);
contact=findViewById(R.id.contact);
joindate=findViewById(R.id.joindate);
addmship=findViewById(R.id.addmship);
cmmdetail=findViewById(R.id.cmmdetail);
attend=findViewById(R.id.attendance);
purchase=findViewById(R.id.purchase);
        data = sharedPrefs.getMemberData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("Member");
bindData();

addmship.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i= new Intent(ViewMember.this,ViewMembership.class);
        startActivity(i);
    }
});
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ViewMember.this,Purchasescreen.class);
                startActivity(i);
            }
        });
        cmmdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ViewMember.this,CurrentMembershipDetail.class);
                startActivity(i);
            }
        });
attend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
    }

    private void bindData() {
        name.setText(data.getMemname());
        contact.setText(data.getContact());
        joindate.setText(data.getDoj());
    }
}
