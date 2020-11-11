package com.wft.sky_gym.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wft.sky_gym.Fragment.Dashboardfragment;
import com.wft.sky_gym.Fragment.EventsFragment;
import com.wft.sky_gym.Fragment.Memberfragment;
import com.wft.sky_gym.Fragment.MembershipplanFragment;
import com.wft.sky_gym.Fragment.ProfileFragment;
import com.wft.sky_gym.Fragment.TrainerFragment;
import com.wft.sky_gym.Fragment.VisitorsFragment;
import com.wft.sky_gym.R;
import com.wft.sky_gym.SharedPrefs;

public class HomeAdmin extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    DrawerLayout drawer;
    RelativeLayout navigation;
    ImageView btn, search;
    ViewPager viewpager;
   public static ImageView editBtn;

    TextView dashboard, profile, member, header, trainer, membershipplan, visitors, events, logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.home);
        loadFragment(new Dashboardfragment());
        btn = findViewById(R.id.btn);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        header = findViewById(R.id.header);
        search = findViewById(R.id.search);
        logout = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        editBtn=findViewById(R.id.edit);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SharedPreferences myPrefs = getSharedPreferences("MY",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                SharedPrefs prefs = new SharedPrefs(HomeAdmin.this);
                prefs.clearAll();
                Intent intent = new Intent(HomeAdmin.this,
                        LoginAdmin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawer.openDrawer(navigation);
            }

        });

        dashboard = findViewById(R.id.dashboard);
        dashboard.setOnClickListener(this);
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(this);
        member = findViewById(R.id.member);
        member.setOnClickListener(this);
        events = findViewById(R.id.events);
        events.setOnClickListener(this);
        trainer = findViewById(R.id.trainer);
        trainer.setOnClickListener(this);
        membershipplan = findViewById(R.id.membershipplans);
        membershipplan.setOnClickListener(this);
        visitors = findViewById(R.id.visitors);
        visitors.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

//                case R.id.dashboard:
//
//                    getSupportFragmentManager().beginTransaction().add(R.id.fragment, new Dashboardfragment()).commit();
//
//                    break;

            case R.id.member:

                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new Memberfragment()).commit();

                break;


            case R.id.profile:


                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new ProfileFragment()).commit();

                break;
            case R.id.trainer:

                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new TrainerFragment()).commit();

                break;
            case R.id.visitors:

                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new VisitorsFragment()).commit();

                break;
            case R.id.membershipplans:

                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new MembershipplanFragment()).commit();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dashboard:
                loadFragment(new Dashboardfragment());
                header.setText("Dashboard");
                editBtn.setVisibility(View.GONE);
                break;

            case R.id.profile:
                search.setVisibility(View.GONE);
                loadFragment(new ProfileFragment());
                header.setText("Profile");
                editBtn.setVisibility(View.VISIBLE);

                break;

            case R.id.member:
                header.setText("Member");
                search.setVisibility(View.VISIBLE);
                loadFragment(new Memberfragment());
                editBtn.setVisibility(View.GONE);
                break;
            case R.id.trainer:
                header.setText("Trainer");
                search.setVisibility(View.VISIBLE);
                loadFragment(new TrainerFragment());
                editBtn.setVisibility(View.GONE);
                break;
            case R.id.membershipplans:
                header.setText("Membership");
                search.setVisibility(View.GONE);
                editBtn.setVisibility(View.GONE);
                loadFragment(new MembershipplanFragment());
                break;
            case R.id.visitors:
                header.setText("Visitors");
                search.setVisibility(View.VISIBLE);
                editBtn.setVisibility(View.GONE);
                loadFragment(new VisitorsFragment());
                break;
            case R.id.events:
                header.setText("Events");
                editBtn.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);
                loadFragment(new EventsFragment());
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }


}

