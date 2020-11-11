package com.wft.sky_gym.Admin;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wft.sky_gym.Adapters.PurchaseAdapter;
import com.wft.sky_gym.R;

public class Purchasescreen extends AppCompatActivity {
    RecyclerView recyclerView;
    PurchaseAdapter purchaseAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.purchasescreen);
        recyclerView= findViewById(R.id.purchaserecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        purchaseAdapter = new PurchaseAdapter(getApplicationContext());
        recyclerView.setAdapter(purchaseAdapter);
    }
}
