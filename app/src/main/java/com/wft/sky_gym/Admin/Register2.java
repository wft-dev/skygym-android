package com.wft.sky_gym.Admin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register2 extends AppCompatActivity implements Spinner.OnItemSelectedListener{
    DatePickerDialog picker;
    Spinner spinner;
    TextView tv1,tv2,tv3,tv4,tv5,dob;
    EditText email, mobileno, password;
    Button done;
    String gender;
    FirebaseAuth firebaseAuth;
     DatabaseReference databaseReference;
     FirebaseDatabase firebaseDatabase;
    RelativeLayout layout;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
ProgressBar progress;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String gname,gid,gadd,fname1,lname1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.register2);

        pref=getSharedPreferences("pref",MODE_PRIVATE);
        editor=pref.edit();
        spinner = findViewById(R.id.gender);
        spinner.setOnItemSelectedListener(this);
        email = findViewById(R.id.email);
        firebaseAuth=FirebaseAuth.getInstance();
        mobileno = findViewById(R.id.mobileno);
        dob = findViewById(R.id.dob);
        password = findViewById(R.id.password);
        done = findViewById(R.id.done);
        layout=findViewById(R.id.layout);
        progress=findViewById(R.id.progress);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        gname=getIntent().getExtras().getString("name");
        gid=getIntent().getExtras().getString("id");
        gadd=getIntent().getExtras().getString("add");
        fname1=getIntent().getExtras().getString("fname");
        lname1=getIntent().getExtras().getString("lname");


        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
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
                picker = new DatePickerDialog(Register2.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



        done.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                if(gender.equals("Select")){
                    Animation shake = AnimationUtils.loadAnimation(Register2.this, R.anim.shake);
                    email.startAnimation(shake);
                    editor.putBoolean("login", false).commit();
                    tv1.setVisibility(View.VISIBLE);
                    Toast.makeText(Register2.this, "Select Gender ", Toast.LENGTH_SHORT).show();
                }
              else  if (email.getText().toString().length() == 0 ||!checkEmailPatern(email.getText().toString())) {
                    Animation shake = AnimationUtils.loadAnimation(Register2.this, R.anim.shake);
                    email.startAnimation(shake);
                    editor.putBoolean("login", false).commit();
                    tv2.setVisibility(View.VISIBLE);
                    Toast.makeText(Register2.this, "Enter Email ", Toast.LENGTH_SHORT).show();

                } else if (password.getText().toString().length() == 0) {
                  Animation shake = AnimationUtils.loadAnimation(Register2.this, R.anim.shake);
                    password.startAnimation(shake);
                    editor.putBoolean("login", false).commit();
                    tv4.setVisibility(View.VISIBLE);
                    Toast.makeText(Register2.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (mobileno.getText().toString().length() == 0||!checkContactPatern(mobileno.getText().toString())) {
                    Animation shake = AnimationUtils.loadAnimation(Register2.this, R.anim.shake);editor.putBoolean("login", false).commit();
                    tv3.setVisibility(View.VISIBLE);
                    mobileno.startAnimation(shake);
                    Toast.makeText(Register2.this, "enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (dob.getText().toString().length() == 0) {
                    Animation shake = AnimationUtils.loadAnimation(Register2.this, R.anim.shake);
                    dob.startAnimation(shake);
                    editor.putBoolean("login", false).commit();
                    tv5.setVisibility(View.VISIBLE);
                    Toast.makeText(Register2.this, "enter Last Name", Toast.LENGTH_SHORT).show();
                }

                else {
                    progress.setVisibility(View.VISIBLE);
                    editor.putBoolean("login", true).commit();
                    firebaseDatabase=FirebaseDatabase.getInstance();
                    databaseReference=firebaseDatabase.getReference("Users");
                    //get all the values
                    final String email1=email.getText().toString();
                    final String contact=mobileno.getText().toString();
                    final String pass=password.getText().toString();
                    final String date=dob.getText().toString();


                    UsersHelperClass helperClass=new UsersHelperClass(gname,gid,gadd,fname1,lname1,date,email1,pass,contact,gender);
                    databaseReference.child(gid).setValue(helperClass);
                    SharedPrefs sharedPreferences = new SharedPrefs(Register2.this);
                    sharedPreferences.createUserDataSession(helperClass);
                    progress.setVisibility(View.GONE);
                           Intent i = new Intent(Register2.this, HomeAdmin.class);
                                startActivity(i);
                 //   register(email1,pass,contact,date,gname,gadd,gid,fname1,lname1);



                }
            }
        });


    }



//    private void register(final String email1, final String pass, final String contact, final String date, final String gname, final String gadd, final String gid, final String fname1, final String lname1) {
//        firebaseAuth.createUserWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    FirebaseUser rUser=firebaseAuth.getCurrentUser();
//                    String userid=rUser.getUid();
//                    databaseReference=FirebaseDatabase.getInstance().getReference("Users").child(userid);
//                    HashMap<String,String> hashMap=new HashMap<>();
//                    hashMap.put("userid",userid);
//                    hashMap.put("gymname",gname);
//                    hashMap.put("gymadd",gadd);
//                    hashMap.put("gymid",gid);
//                    hashMap.put("fname",fname1);
//                    hashMap.put("lname",lname1);
//
//                    hashMap.put("email",email1);
//                    hashMap.put("password",pass);
//                    hashMap.put("mobile no",contact);
//                    hashMap.put("dob",date);
//
//                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                progress.setVisibility(View.GONE);
//                                Intent i = new Intent(Register2.this, HomeAdmin.class);
//                                startActivity(i);
//
//
//                            }
//                            else{
//                                progress.setVisibility(View.GONE);
//                                Toast.makeText(Register2.this,"registration is not completed",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }else{
//                    progress.setVisibility(View.GONE);
//Toast.makeText(Register2.this,"data not accepted",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//    }





    private void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public boolean checkEmailPatern(String email) {
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean checkContactPatern(String contact) {
        Pattern pattern = Pattern.compile(MobilePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender= String.valueOf(spinner.getSelectedItem());
        Log.v("gender",gender);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}








