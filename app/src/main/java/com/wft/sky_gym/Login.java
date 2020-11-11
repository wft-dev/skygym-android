package com.wft.sky_gym;

import android.content.Context;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wft.sky_gym.Admin.Register1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    EditText gymid,email,password;
    Button login;
    LinearLayout layout;
    TextView forgotpass,tv1,tv2,tv3;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.login);
        gymid=findViewById(R.id.gymid);
        layout=findViewById(R.id.layout);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        forgotpass=findViewById(R.id.forgotpass);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gymid.getText().toString().length()==0){
                    tv1.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(Login.this, R.anim.shake);
                    gymid.startAnimation(shake);
                    Toast.makeText(Login.this,"Gym ID",Toast.LENGTH_SHORT).show();
                }
                else if (email.getText().toString().length()==0 ||!checkEmailPatern(email.getText().toString())){
                    tv2.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(Login.this, R.anim.shake);
                    email.startAnimation(shake);
                    Toast.makeText(Login.this,"enter First Name",Toast.LENGTH_SHORT).show();
                }
                else if (password.getText().toString().length()==0){
                    tv3.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(Login.this, R.anim.shake);
                    password.startAnimation(shake);
                    Toast.makeText(Login.this,"enter First Name",Toast.LENGTH_SHORT).show();
            }

            }
        });

    }
    public boolean checkEmailPatern(String email) {
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
