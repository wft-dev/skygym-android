package com.wft.sky_gym.Admin;

import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Comparator;

public class EventHelper {
    String title, date, detail, sdate, edate;

    public EventHelper() {
    }

    public EventHelper(String title, String date, String detail, String sdate, String edate  ) {
        this.title = title;
        this.date= date;
        this.detail= detail;
        this.sdate= sdate;
        this.edate= edate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }
}
