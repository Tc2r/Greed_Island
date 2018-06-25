package com.tc2r.greedisland.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.tc2r.greedisland.R;

import java.util.Random;


/**
 * Created by Nudennie.white on 3/26/18.
 */

public class PlayerInfo extends AppCompatActivity {

    private static PlayerInfo INSTANCE = new PlayerInfo();
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Context context;

    private String tName = "Chrollo";

    public static PlayerInfo getInstance() {
        return(INSTANCE);
    }

    private PlayerInfo() {};


    public void init(Context context) {
        this.context = context.getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String Get_Pref_Theme_Key(Context context) {
        return context.getApplicationContext().getResources().getString(R.string.pref_theme_selection_key);
    }

    public String Get_Pref_First_Run_Key(Context context) {
        return context.getApplicationContext().getResources().getString(R.string.pref_initiate_key);
    }

    public String Get_Pref_First_Tut_Key(Context context) {
        return context.getApplicationContext().getResources().getString(R.string.pref_first_time_tut_key);
    }


    public Boolean GetFirstRun(Context context, Boolean defaultValue) {
        return preferences.getBoolean(Get_Pref_First_Run_Key(context), defaultValue);
    }

    public void SetFirstRun(Context context, Boolean setRun) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.putBoolean(Get_Pref_First_Run_Key(context), setRun);
        editor.apply();
    }

    public Boolean GetTutRan(Context context) {
        return preferences.getBoolean(Get_Pref_First_Tut_Key(context), false);
    }

    public void SetTutRan(Context context, Boolean setRun) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.putBoolean(getString(R.string.pref_first_time_tut_key), setRun);
        editor.apply();
    }

    public void SetRandomName(Context context) {
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor tempName = setting.edit();
        Random rand = new Random();
        String[] tempNames = context.getResources().getStringArray(R.array.random_names);
        int n = rand.nextInt(tempNames.length);
        tName = tempNames[n];
        tempName.putString("TempName", tName);
        tempName.commit();
    }

    public String GetHunterName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getResources().getString(R.string.pref_hunter_name_key), tName);

    }

    public int GetHunterID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(context.getResources().getString(R.string.pref_hunter_id_key), 0);
    }

    public String GetCurrentHome(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getResources().getString(R.string.pref_current_home_key), context.getResources().getString(R.string.pref_town_default));
    }
}
