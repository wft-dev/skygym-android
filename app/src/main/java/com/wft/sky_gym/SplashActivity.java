package com.wft.sky_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.wft.sky_gym.Admin.HomeAdmin;
import com.wft.sky_gym.Admin.LoginAdmin;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.activity_main);



        pref = getSharedPreferences("pref",MODE_PRIVATE);
        editor = pref.edit();


        Handler h =  new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(pref.getBoolean("login",false)){
//                    Intent i = new Intent(SplashActivity.this,HomeAdmin.class);
//                    startActivity(i);
//                    finish();
//                }else{
//                    Intent i = new Intent(SplashActivity.this,LoginAdmin.class);
//                    startActivity(i);
//                    finish();
//                }

                SharedPrefs sharedPrefs=new SharedPrefs(SplashActivity.this);
                if(sharedPrefs.getUserData()!=null){
                    Intent i = new Intent(SplashActivity.this,HomeAdmin.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashActivity.this,LoginAdmin.class);
                    startActivity(i);
                    finish();
                }
            }
        },3000);


    }


}

