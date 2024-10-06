package com.example.medicalplants.Setting;

import android.content.Context;
import android.content.SharedPreferences;

public class Default_MedicalPlants {
    Context m_context;
    public static final String KEY_ID="id";
    public static final String MYPrefrences="Preferences_MedicalPlants";
    public SharedPreferences sharedPreferences;

    public Default_MedicalPlants(Context context)
    {
        m_context=context;
        sharedPreferences=m_context.getSharedPreferences(MYPrefrences, Context.MODE_PRIVATE);

    }

    public void saveID(String id)
    {
        SharedPreferences.Editor edit= this.sharedPreferences.edit();
        edit.putString(KEY_ID, id);
        edit.commit();

    }

    public String get_id()
    {
        return  this.sharedPreferences.getString(KEY_ID,"");
    }





}
