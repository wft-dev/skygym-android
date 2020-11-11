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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.wft.sky_gym.Adapters.PageAdapter;
import com.wft.sky_gym.Fragment.MemberMembershipFragment;
import com.wft.sky_gym.Fragment.MemberProfileFragment;
import com.wft.sky_gym.Fragment.Memberfragment;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class AddMember extends AppCompatActivity {
    TabLayout tablayout;
LinearLayout l1;
    SharedPrefs sharedPrefs;
    TabItem profile, membership;
PagerAdapter pageAdapter;
    ViewPager viewpager;
    MemberHelper data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.addmember);
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        membership=findViewById(R.id.membership);

        l1=findViewById(R.id.l1);
        profile=findViewById(R.id.profile);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pageAdapter);
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pageAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pageAdapter.notifyDataSetChanged();

                }


            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

    }

    private void hideKeyboard(View view) {

        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }



}

