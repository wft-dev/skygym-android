package com.wft.sky_gym.Admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wft.sky_gym.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TrainerAttendance extends AppCompatActivity {
    RelativeLayout date;
    TextView check,startdate,enddate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();//hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//enable full screen
        setContentView(R.layout.trainerattendance);
        date=findViewById(R.id.date);
        check=findViewById(R.id.check);
        startdate=findViewById(R.id.startdate);
        enddate=findViewById(R.id.enddate);
        final Calendar calendar = Calendar.getInstance();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date1 = calendar.getTime();
                //current date to check that our week lies in same month or not
                SimpleDateFormat checkformate = new SimpleDateFormat("DD/MM/yyyy");
                String currentCheckdate= checkformate.format(date1);

                int weekn = calendar.get(Calendar.WEEK_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year  = calendar.get(Calendar.YEAR);
                //resat calender without date
                calendar.clear();
                calendar.setFirstDayOfWeek(Calendar.SUNDAY);
                calendar.set(Calendar.WEEK_OF_MONTH,weekn);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.YEAR,year);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date datef = calendar.getTime();
                //move date to 6 days + to get last date of week
                Long timeSixDayPlus = calendar.getTimeInMillis()+518400000L;
                Date dateL = new Date(timeSixDayPlus);
                String firtdate = simpleDateFormat.format(datef);
                String lastdate = simpleDateFormat.format(dateL);
                String firtdateCheck = checkformate.format(datef);
                String lastdateCheck = checkformate.format(dateL);
                startdate.setText(firtdate);
                enddate.setText(lastdate);
                //if our week lies in two different months then we show only current month week part only
                if (!firtdateCheck.toString().equalsIgnoreCase(currentCheckdate)) {
                    firtdate = "1" + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
                }
                if (!lastdateCheck.toString().equalsIgnoreCase(currentCheckdate)) {
                    int ma = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    lastdate = String.valueOf(ma) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
                }
                Log.e("current","=>>"+firtdate+" to "+lastdate);
            }
        });
    }
}
