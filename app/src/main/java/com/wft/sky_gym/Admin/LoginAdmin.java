package com.wft.sky_gym.Admin;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wft.sky_gym.Login;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAdmin extends AppCompatActivity {
    RelativeLayout layout;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText email, password;
    ProgressBar progress;
    TextView register, tv1, tv2;
    Button login, mtlogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.loginadmin);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();
        login = findViewById(R.id.login);
        layout = findViewById(R.id.layout);
        register = findViewById(R.id.register);
        progress = findViewById(R.id.progress);
        mtlogin = findViewById(R.id.mtlogin);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginAdmin.this,Login.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String pass = password.getText().toString();
                if (email.getText().toString().trim().length() == 0 || !checkEmailPatern(email.getText().toString())) {
                    editor.putBoolean("login", false).commit();
                    Animation shake = AnimationUtils.loadAnimation(LoginAdmin.this, R.anim.shake);
                    email.startAnimation(shake);
                    tv1.setVisibility(View.VISIBLE);
                    ;

                } else if (password.getText().toString().trim().length() == 0) {
                    editor.putBoolean("login", false).commit();
                    Animation shake = AnimationUtils.loadAnimation(LoginAdmin.this, R.anim.shake);
                    password.startAnimation(shake);
                    tv2.setVisibility(View.VISIBLE);
                } else {
                    progress.setVisibility(View.VISIBLE);
                    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                    layoutParams.dimAmount = 0.75f;
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    getWindow().setAttributes(layoutParams);

                    //submit(email1,pass);
                    editor.putBoolean("login", true).commit();
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(view.GONE);
                    isuser();
                }
            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginAdmin.this, Register1.class);
                startActivity(i);
            }
        });


    }

    private boolean checkEmailPatern(String email) {
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    private void isuser() {
        final String userenteredemail = email.getEditableText().toString().trim();
        final String userenteredpassword = password.getEditableText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean isExists=false;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UsersHelperClass data= dataSnapshot.getValue(UsersHelperClass.class);
                    String pass=data.password;
                    String email=data.email;
                    if(email.equals(userenteredemail) && pass.equals(userenteredpassword)){
                        isExists=true;
                        SharedPrefs sharedPreferences = new SharedPrefs(LoginAdmin.this);
                        sharedPreferences.createUserDataSession(data);
                        Intent intent = new Intent(LoginAdmin.this,HomeAdmin.class);
                        startActivity(intent);
                    }

                }
                if(!isExists){
                    progress.setVisibility(View.GONE);
                    Toast.makeText(LoginAdmin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.setVisibility(View.GONE);
Log.v("errr",error.getMessage());
            }
        });

//        Query checkUser = reference.orderByChild("email").equalTo(userenteredemail);
//
//
//        checkUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    UsersHelperClass data = snapshot.getValue(UsersHelperClass.class);
//                    if (data.password.equals(userenteredpassword)) {
//                        String dobfromDB = snapshot.child("gid").child("dob").getValue(String.class);
//                        String fnamefromDB = snapshot.child("gid").child("fname").getValue(String.class);
//                        String lnamefromDB = snapshot.child("gid").child("lname").getValue(String.class);
//                        String gymnamefromDB = snapshot.child("gid").child("gymname").getValue(String.class);
//                        String gymaddfromDB = snapshot.child("gid").child("gymadd").getValue(String.class);
//                        String gymidfromDB = snapshot.child("gid").child("gymid").getValue(String.class);
//                        String emailfromDB = snapshot.child("gid").child("email").getValue(String.class);
//                        String mobileNofromDB = snapshot.child("gid").child("mobile no").getValue(String.class);
//                        progress.setVisibility(View.GONE);
//                        Intent i = new Intent(LoginAdmin.this, HomeAdmin.class);
//                        i.putExtra("dob", dobfromDB);
//                        i.putExtra("fname", fnamefromDB);
//                        i.putExtra("lname", lnamefromDB);
//                        i.putExtra("gymname", gymnamefromDB);
//                        i.putExtra("gymadd", gymaddfromDB);
//                        i.putExtra("gymid", gymidfromDB);
//                        i.putExtra("email", emailfromDB);
//                        i.putExtra("mobile no", mobileNofromDB);
//                        startActivity(i);
//                    } else {
//                        progress.setVisibility(View.GONE);
//                        Toast.makeText(LoginAdmin.this, "error", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}




//    private void submit(String email1, String pass) {
//        firebaseAuth.signInWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    progress.setVisibility(View.GONE);
//                    Intent i = new Intent(LoginAdmin.this, HomeAdmin.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                    finish();
//
//                }
//                else {
//                    progress.setVisibility(View.GONE);
//                    Toast.makeText(LoginAdmin.this,"not a valid user",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//    }






