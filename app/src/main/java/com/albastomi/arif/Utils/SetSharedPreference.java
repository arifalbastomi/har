package com.albastomi.arif.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class SetSharedPreference {
    private Context ctx;
    private SharedPreferences mSettingVariabel;

    public static final String loginemailKey = "loginemailKey";
    public static final String loginnamaKey = "loginnamaKey";
    public static final String loginiduserKey = "loginiduserKey";

    public static final String projectidKey = "projectidKey";
    public static final String syncIntervalKey = "syncIntervalKey";
    public static final String insertIntervalKey = "insertIntervalKey";

    public SetSharedPreference(Context ctx) {
        this.ctx = ctx;
        mSettingVariabel = ((Activity)ctx).getSharedPreferences("SettingVariabel", Context.MODE_PRIVATE);
    }

    public void setIsiStringSharedPreferences(String key, String isi){
        SharedPreferences.Editor editor = mSettingVariabel.edit();
        editor.putString(key,isi);
        editor.apply();
    }

    public String getStringSharedPreferences(String key){
        return mSettingVariabel.getString(key,"");
    }

    public void setemailLogin(String email){
        setIsiStringSharedPreferences(loginemailKey,email);
    }

    public String getemailLogin(){
        return getStringSharedPreferences(loginemailKey);
    }

    public void setnamaLogin(String nama){
        setIsiStringSharedPreferences(loginnamaKey, nama );
    }

    public String getnamaLogin(){
        return getStringSharedPreferences(loginnamaKey);
    }

    public void setidUser(String iduser){
        setIsiStringSharedPreferences(loginiduserKey, iduser );
    }

    public String getidUser(){
        return getStringSharedPreferences(loginiduserKey);
    }


    public void setProjectid(String project_id){
        setIsiStringSharedPreferences(projectidKey, project_id );
    }

    public String getProjectId(){
        return getStringSharedPreferences(projectidKey);
    }


    public void setSyncInterval(String sync){
        setIsiStringSharedPreferences(syncIntervalKey, sync );
    }

    public String getSyncInterval(){
        return getStringSharedPreferences(syncIntervalKey);
    }


    public void setInsertInterval(String sync){
        setIsiStringSharedPreferences(insertIntervalKey, sync );
    }

    public String getInsertInterval(){
        return getStringSharedPreferences(insertIntervalKey);
    }







}
