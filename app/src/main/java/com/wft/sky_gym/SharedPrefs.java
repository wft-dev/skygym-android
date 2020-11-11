package com.wft.sky_gym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.wft.sky_gym.Admin.EventHelper;
import com.wft.sky_gym.Admin.LoginAdmin;
import com.wft.sky_gym.Admin.MemberHelper;
import com.wft.sky_gym.Admin.MembershipHelper;
import com.wft.sky_gym.Admin.TrainerHelper;
import com.wft.sky_gym.Admin.UsersHelperClass;
import com.wft.sky_gym.Admin.VisitorHelper;

import java.util.HashMap;

public class SharedPrefs {

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "SkyGym";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "userData";


    // Constructor
    public SharedPrefs(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
public void  createMemberDataSession(MemberHelper member){
    Gson gson = new Gson();
    String json = gson.toJson(member);
    editor.putString(KEY_NAME, json);
    editor.commit();
}
    public void  createVisitorDataSession(VisitorHelper visitor){
        Gson gson = new Gson();
        String json = gson.toJson(visitor);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }
    public void  createEventDataSession(EventHelper event){
        Gson gson = new Gson();
        String json = gson.toJson(event);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }
    public void  createMembershipDataSession(MembershipHelper membership){
        Gson gson = new Gson();
        String json = gson.toJson(membership);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }
    public void  createTrainerDataSession(TrainerHelper trainer){
        Gson gson = new Gson();
        String json = gson.toJson(trainer);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }
    /**
     * Create login session
     * */
    public void createUserDataSession(UsersHelperClass usersHelperClass){
        // Storing login value as TRUE

//set variables of 'myObject', etc.


        Gson gson = new Gson();
        String json = gson.toJson(usersHelperClass);
        editor.putString(KEY_NAME, json);
        editor.commit();
    }

    public void clearAll(){
        editor.clear();
        editor.commit();
    }

    public  void setStringValue(String key, String value){
        editor.putString(key,value);
        editor.commit();


    }


    public String getStringValue(String key){
      return   pref.getString(key,"");

    }




    public  void setIntValue(String key, Integer value){
        editor.putInt(key,value);
        editor.commit();


    }


    public Integer getIntValue(String key){
        return   pref.getInt(key,0);

    }
    public UsersHelperClass getUserData(){
        Gson gson = new Gson();
        String json = pref.getString(KEY_NAME, "");
        UsersHelperClass obj = gson.fromJson(json, UsersHelperClass.class);
        return obj;
    }
    public MemberHelper getMemberData(){
        Gson gson = new Gson();
        String json = pref.getString(KEY_NAME, "");
        MemberHelper obj = gson.fromJson(json, MemberHelper.class);
        return obj;

    }
    public VisitorHelper getVisitorData(){
        Gson gson = new Gson();
        String json = pref.getString(KEY_NAME, "");
        VisitorHelper obj = gson.fromJson(json, VisitorHelper.class);
        return obj;

    }
    public TrainerHelper getTrainerData(){
        Gson gson = new Gson();
        String json = pref.getString(KEY_NAME, "");
        TrainerHelper obj = gson.fromJson(json, TrainerHelper.class);
        return obj;

    }
    public MembershipHelper getMembershipData(){
        Gson gson = new Gson();
        String json = pref.getString(KEY_NAME, "");
        MembershipHelper obj = gson.fromJson(json, MembershipHelper.class);
        return obj;

    }
    public EventHelper getEventData(){
        Gson gson = new Gson();
        String json = pref.getString(KEY_NAME, "");
        EventHelper obj = gson.fromJson(json, EventHelper.class);
        return obj;

    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginAdmin.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }




    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));


        // return user
        return user;
    }
    public HashMap<String, String> getMember(){
        HashMap<String, String> member = new HashMap<String, String>();
        // user name
        member.put(KEY_NAME, pref.getString(KEY_NAME, null));


        // return user
        return member ;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginAdmin.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}