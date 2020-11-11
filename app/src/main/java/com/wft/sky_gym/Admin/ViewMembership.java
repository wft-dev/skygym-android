package com.wft.sky_gym.Admin;

import android.annotation.SuppressLint;
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

public class ViewMembership extends AppCompatActivity {
    EditText title,details, amount;
    TextView startdate,enddate;
    Button  done;
    DatabaseReference refrence;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    SharedPrefs sharedPrefs;
    MembershipHelper data;
    DatePickerDialog picker;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.viewmembership);
        title=findViewById(R.id.title);
        details= findViewById(R.id.details);
        amount= findViewById(R.id.amount);
        done=findViewById(R.id.done);
        startdate= findViewById(R.id.sdate);
        enddate =findViewById(R.id.enddate);
        done= findViewById(R.id.done);
        sharedPrefs = new SharedPrefs(ViewMembership.this);


        data = sharedPrefs.getMembershipData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("MembershipPlan");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=title.getText().toString();
                String price= amount.getText().toString();
                String  detail= details.getText().toString();
                String sdate= startdate.getText().toString();
                String edate= enddate.getText().toString();
                if (title.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(ViewMembership.this, R.anim.shake);
                    title.startAnimation(shake);
                }
               else if(details.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(ViewMembership.this, R.anim.shake);
                    details.startAnimation(shake);
                }
               else if (amount.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(ViewMembership.this, R.anim.shake);
                    amount.startAnimation(shake);
                }
               else if (startdate.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(ViewMembership.this, R.anim.shake);
                    startdate.startAnimation(shake);

                } else if (enddate.getText().toString().trim().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(ViewMembership.this, R.anim.shake);
                    enddate.startAnimation(shake);
                }
               else {
                    MembershipHelper membership=new MembershipHelper(name,price,detail,sdate,edate);
                    refrence.child(name).setValue(membership);
                    SharedPrefs sharedPreferences = new SharedPrefs(ViewMembership.this);
                    sharedPreferences.createMembershipDataSession(membership);
                    Intent i= new Intent(ViewMembership.this, HomeAdmin.class);
                    startActivity(i);

                }
            }
        });
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ViewMembership.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ViewMembership.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                enddate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }
}
