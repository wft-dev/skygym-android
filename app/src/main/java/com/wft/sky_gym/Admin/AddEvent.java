package com.wft.sky_gym.Admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wft.sky_gym.Fragment.EventsFragment;
import com.wft.sky_gym.Fragment.ProfileFragment;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AppCompatActivity {
    DatePickerDialog picker;
    EditText title,details;
    TextView date,stime,etime;
Button update;
ImageView btn;
LinearLayout screen;
    DatabaseReference refrence;
    EventHelper data;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    SharedPrefs sharedPrefs;
    String Title,Details,Date,Stime,Etime;
//LinearLayout screen;
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
        screen=findViewById(R.id.screen);
        btn=findViewById(R.id.btn);
        Title=getIntent().getStringExtra("title");
        Details=getIntent().getStringExtra("detail");
        Date=getIntent().getStringExtra("date");
        Stime=getIntent().getStringExtra("stime");
        Etime=getIntent().getStringExtra("etime");
        title.setText(Title);
        details.setText(Details);
        date.setText(Date);
        stime.setText(Stime);
        etime.setText(Etime);

        data = sharedPrefs.getEventData();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refrence = FirebaseDatabase.getInstance().getReference().child("Event");
//        bindData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//               final FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
//               ft.replace(R.id.screen,new EventsFragment(),"event");
//               ft.commit();

//                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new EventsFragment()).commit();
//                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.screen,new EventsFragment()).commit();
//                FragmentManager fm=getSupportFragmentManager();
//                EventsFragment fragment= new EventsFragment();
//                fm.beginTransaction().replace(fragment).commit();
                Intent i= new Intent(AddEvent.this,HomeAdmin.class);
                startActivity(i);


            }
        });
        stime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        stime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        etime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
       update.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         String name = title.getText().toString();
                                         String detail = details.getText().toString();
                                         String start = stime.getText().toString();
                                         String end = etime.getText().toString();
                                         String doe = date.getText().toString();
                                         if (title.getText().toString().trim().length() == 0) {
                                             Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                                             title.startAnimation(shake);
                                         } else if (details.getText().toString().trim().length() == 0) {
                                             Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                                             details.startAnimation(shake);
                                         } else if (stime.getText().toString().trim().length() == 0) {
                                             Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                                             stime.startAnimation(shake);

                                         } else if (etime.getText().toString().trim().length() == 0) {
                                             Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                                             etime.startAnimation(shake);
                                         } else if (date.getText().toString().trim().length() == 0) {
                                             Animation shake = AnimationUtils.loadAnimation(AddEvent.this, R.anim.shake);
                                             date.startAnimation(shake);
                                         } else {

                                             SharedPrefs sharedPreferences = new SharedPrefs(AddEvent.this);

                                             EventHelper event = new EventHelper(name, doe, detail, start, end);
                                             sharedPreferences.createEventDataSession(event);
                                             refrence.child(name).setValue(event);
                                             Query db = refrence.child("title").orderByChild("date");
                                             updateDataEvent();
                                             Intent i = new Intent(AddEvent.this, HomeAdmin.class);
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

    private void Inset() {
        Map<String,Object> map= new HashMap<>();
        map.put("title",title.getText().toString());
        map.put("detail",details.getText().toString());
        map.put("date",date.getText().toString());
        map.put("stime",stime.getText().toString());
        map.put("etime",etime.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Event");

    }

    //    private void bindData() {
//        title.setText(data.getTitle());
//        details.setText(data.getDetail());
//        date.setText(data.getDate());
//        stime.setText(data.getSdate());
//        etime.setText(data.getEdate());
//    }
    public void updateDataEvent() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event");
        final String Title = title.getText().toString();
        final String Detail = details.getText().toString();
        final String Date = date.getText().toString();
        final String Stime = stime.getText().toString();
        final String Etime = etime.getText().toString();
        EventHelper eventHelper = new EventHelper(Title,Date,Detail,Stime,Etime);
        reference.child(Title).setValue(eventHelper);
        sharedPrefs.createEventDataSession(eventHelper);
    }


}