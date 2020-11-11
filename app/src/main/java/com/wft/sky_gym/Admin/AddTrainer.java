package com.wft.sky_gym.Admin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wft.sky_gym.Fragment.TrainerFragment;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTrainer extends AppCompatActivity {
    DatePickerDialog picker;
    LinearLayout l1;
    EditText fname,lname,gender,password,email,address,contact,id,salary,shifts,time;
    TextView doj,dob;
    Button attendance,update;
    DatabaseReference refrence;
    TrainerHelper data;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    SharedPrefs sharedPrefs;
    ImageView camera,uploadid,btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.addtrainer);
        l1=findViewById(R.id.l1);
salary=findViewById(R.id.salary);
        sharedPrefs = new SharedPrefs(AddTrainer.this);


        data = sharedPrefs.getTrainerData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("Trainer");


        gender=findViewById(R.id.gender);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        shifts=findViewById(R.id.shifts);
        time=findViewById(R.id.time);
        btn=findViewById(R.id.btn);


      fname=findViewById(R.id.fname);
      lname=findViewById(R.id.lname);
        address=findViewById(R.id.address);
        id=findViewById(R.id.id);
        contact=findViewById(R.id.contact);
        doj=findViewById(R.id.doj);
        dob=findViewById(R.id.dob);
         attendance=findViewById(R.id.attendance);
       update=findViewById(R.id.update);

        camera=findViewById(R.id.camera);
        uploadid=findViewById(R.id.uploadid);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddTrainer.this,TrainerAttendance.class);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname=fname.getText().toString();
                String lastname=fname.getText().toString();
                String tid=id.getText().toString();
                String mobile=contact.getText().toString();
                String email1=email.getText().toString();
                String tadd= address.getText().toString();
                String gen = gender.getText().toString();
                String salry= salary.getText().toString();
                String days=shifts.getText().toString();
                String timings= time.getText().toString();
                String birth= dob.getText().toString();
                String join= doj.getText().toString();


                if (fname.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    fname.startAnimation(shake);
                }
                else if (lname.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    lname.startAnimation(shake);
                }
                else if (id.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    id.startAnimation(shake);
                }
                else if (contact.getText().toString().trim().length()==0||!checkContactPatern(contact.getText().toString())){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    contact.startAnimation(shake);
                }
                else if (email.getText().toString().trim().length()==0||!checkEmailPatern(email.getText().toString())){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    email.startAnimation(shake);
                }
               else if (address.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    address.startAnimation(shake);

                }
               else if (gender.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    gender.startAnimation(shake);
                }
               else if (salary.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    salary.startAnimation(shake);
                }
               else if (shifts.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    shifts.startAnimation(shake);
                }
               else if (time.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    time.startAnimation(shake);

                }
               else if (dob.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    dob.startAnimation(shake);
                }
               else if (doj.getText().toString().trim().length()==0){
                    Animation shake = AnimationUtils.loadAnimation(AddTrainer.this, R.anim.shake);
                    doj.startAnimation(shake);

                }
               else{
                    TrainerHelper trainer=new TrainerHelper(gen,mobile,birth,join,email1,firstname,lastname,salry,days,timings,tid);
                    refrence.child(tid).setValue(trainer);
                    SharedPrefs sharedPreferences = new SharedPrefs(AddTrainer.this);
                    sharedPreferences.createTrainerDataSession(trainer);
                    Intent i= new Intent(AddTrainer.this, HomeAdmin.class);
                    startActivity(i);

                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTrainer.this, TrainerFragment.class);
                startActivity(intent);

            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddTrainer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddTrainer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                doj.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        l1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });

    }
    private void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }
    public boolean checkContactPatern(String contact) {
        Pattern pattern = Pattern.compile(MobilePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }
    public boolean checkEmailPatern(String email) {
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
