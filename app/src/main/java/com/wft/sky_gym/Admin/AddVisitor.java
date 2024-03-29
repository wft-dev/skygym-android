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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddVisitor extends AppCompatActivity {
    DatePickerDialog picker;
    LinearLayout l1;
     TextView doj, dov;
     Button update;
    DatabaseReference refrence;
    VisitorHelper data;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    SharedPrefs sharedPrefs;
EditText fname,lname,email,contact,address,visits, gender;
String Fname,Lname,Contact,Email,Address,Visits,Gender,Doj,Dov;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
            getSupportActionBar().hide();//hide the title bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
            setContentView(R.layout.addvisitor);
            sharedPrefs = new SharedPrefs(AddVisitor.this);
            data=sharedPrefs.getVisitorData();
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            refrence = FirebaseDatabase.getInstance().getReference().child("Visitor");
l1=findViewById(R.id.l1);
fname=findViewById(R.id.fname);
doj=findViewById(R.id.doj);
dov=findViewById(R.id.dov);
update=findViewById(R.id.update);
lname=findViewById(R.id.lname);
email=findViewById(R.id.email);
contact= findViewById(R.id.contact);
address=findViewById(R.id.address);
visits=findViewById(R.id.visits);
gender=findViewById(R.id.gender);
Fname=getIntent().getStringExtra("fname");
Lname=getIntent().getStringExtra("lname");
Contact=getIntent().getStringExtra("contact");
Email= getIntent().getStringExtra("email");
Address= getIntent().getStringExtra("add");
Visits= getIntent().getStringExtra("visits");
Gender=getIntent().getStringExtra("gender");
Doj=getIntent().getStringExtra("doj");
Dov=getIntent().getStringExtra("dov");
fname.setText(Fname);
lname.setText(Lname);
email.setText(Email);
contact.setText(Contact);
address.setText(Address);
visits.setText(Visits);
gender.setText(Gender);
doj.setText(Doj);
dov.setText(Dov);




       //  bindData();
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String Fname=fname.getText().toString();
        String Lname= lname.getText().toString();
        String Email =email.getText().toString();
        String Contact= contact.getText().toString();
        String Address= address.getText().toString();
        String Visits=visits.getText().toString();
        String Gender= gender.getText().toString();
        String Doj= doj.getText().toString();
        String Dov= dov.getText().toString();
        if (fname.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            fname.startAnimation(shake);
        }
        else if(lname.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            lname.startAnimation(shake);
        }
        else if (email.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            email.startAnimation(shake);
        }
        else if (contact.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            contact.startAnimation(shake);
        }
        else if (address.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            address.startAnimation(shake);
        }
        else if(visits.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            visits.startAnimation(shake);
        }
        else if (contact.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            contact.startAnimation(shake);
        }
        else if (doj.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            doj.startAnimation(shake);
        }

        else if (dov.getText().toString().trim().length()==0){
            Animation shake = AnimationUtils.loadAnimation(AddVisitor.this, R.anim.shake);
            dov.startAnimation(shake);
        }
        else {
            Intent i= new Intent(AddVisitor.this, HomeAdmin.class);
            VisitorHelper visitors=new VisitorHelper(Fname,Lname,Email,Address,Visits,Doj,Dov,Gender,Contact);
            refrence.child(Fname).setValue(visitors);
            SharedPrefs sharedPreferences = new SharedPrefs(AddVisitor.this);
            sharedPreferences.createVisitorDataSession(visitors);
            updateDataVisitor();
            startActivity(i);
        }
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
            dov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(AddVisitor.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    dov.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                    picker = new DatePickerDialog(AddVisitor.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    doj.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    picker.show();
                }
            });

        }
    private void updateDataVisitor() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Visitor");

        final String firstname = fname.getText().toString();
        final String lastname = lname.getText().toString();
        final String email1 = email.getText().toString();
        final String mobile = contact.getText().toString();
        final String dovv = dov.getText().toString();
       final String add=address.getText().toString();
       final String visit= visits.getText().toString();
        final String dojj=doj.getText().toString();
        final String genderr = gender.getText().toString();
        VisitorHelper visitor = new VisitorHelper(firstname,lastname,email1,add,visit,dojj,dovv,genderr,mobile);
        reference.child(firstname).setValue(visitor);
        sharedPrefs.createVisitorDataSession(visitor);
    }
//    private void bindData() {
//        fname.setText(data.getFname());
//        lname.setText(data.getLname());
//        gender.setText(data.getGender());
//        email.setText(data.getEmail());
//        contact.setText(data.getContact());
//        dov.setText(data.getDov());
//        visits.setText(data.getVisits());
//       address.setText(data.getAddress());
//        doj.setText(data.getDoj());
//
//    }

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


