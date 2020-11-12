package com.wft.sky_gym.Admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {
    DatePickerDialog picker;
    EditText title,details,stime,etime;
    TextView date;
Button update;
    DatabaseReference refrence;
    EventHelper data;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.addevent);
        sharedPrefs = new SharedPrefs(AddEvent.this);
title=findViewById(R.id.title);
details=findViewById(R.id.details);
stime=findViewById(R.id.stime);
etime=findViewById(R.id.etime);
date=findViewById(R.id.date);
update=findViewById(R.id.update);

        data = sharedPrefs.getEventData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("Event");
        bindData();
       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name=title.getText().toString();
               String detail=details.getText().toString();
               String  start=stime.getText().toString();
               String end=etime.getText().toString();
               String doe= date.getText().toString();
               if (title.getText().toString().trim().length()==0){
                   Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                   title.startAnimation(shake);
               }
               else  if (details.getText().toString().trim().length()==0){
                   Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                   details.startAnimation(shake);
               }
               else if (stime.getText().toString().trim().length()==0){
                   Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                   stime.startAnimation(shake);

               }
               else if (etime.getText().toString().trim().length()==0){
                   Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                   etime.startAnimation(shake);
               }
               else if (date.getText().toString().trim().length()==0){
                   Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                   date.startAnimation(shake);
               }
               else{
                   EventHelper event=new EventHelper(name,doe,detail,start,end);
                   refrence.child(name).setValue(event);
                   SharedPrefs sharedPreferences = new SharedPrefs(AddEvent.this);
                   sharedPreferences.createEventDataSession(event);
                   Intent i= new Intent(AddEvent.this, HomeAdmin.class);
                   startActivity(i);


               }

           }
       });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddEvent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

    private void bindData() {
        title.setText(data.getTitle());
        details.setText(data.getDetail());
        date.setText(data.getDate());
        stime.setText(data.getSdate());
        etime.setText(data.getEdate());
    }


}