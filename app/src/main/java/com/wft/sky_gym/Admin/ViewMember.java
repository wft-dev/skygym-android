package com.wft.sky_gym.Admin;

import android.os.Bundle;
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
    TextView name,contact, joindate;
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

        data = sharedPrefs.getMemberData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("Member");
bindData();




    }

    private void bindData() {
        name.setText(data.getMemname());
        contact.setText(data.getContact());
        joindate.setText(data.getDoj());
    }
}
