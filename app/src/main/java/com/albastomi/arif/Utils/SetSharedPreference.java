package com.albastomi.arif.Utils;
import android.app.Activity;
import android.app.Service;
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
    public static final String attemptKey = "attemptKey";
    public static final String loginKey = "loginKey";
    public static final String aktifKey = "aktifKey";
    public static final String activityKey = "activityKey";
    public static final String signalKey = "signalKey";
    public static final String durationKey = "durationKey";

    public SetSharedPreference(Context ctx,boolean  isActivity) {
        this.ctx = ctx;
        if(isActivity){
            mSettingVariabel = ((Activity)ctx).getSharedPreferences("SettingVariabel", Context.MODE_PRIVATE);
        }else{
            mSettingVariabel = ((Service)ctx).getSharedPreferences("SettingVariabel", Context.MODE_PRIVATE);
        }

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


    public void setAttempt(String attempt){
        setIsiStringSharedPreferences(attemptKey, attempt );
    }

    public String getAttempt(){
        return getStringSharedPreferences(attemptKey);
    }


    public void setLogin(String login){
        setIsiStringSharedPreferences(loginKey, login );
    }

    public String getLogin(){
        return getStringSharedPreferences(loginKey);
    }

    public void setAktif(String aktif){
        setIsiStringSharedPreferences(aktifKey, aktif );
    }

    public String getAktif(){
        return getStringSharedPreferences(aktifKey);
    }


    public void setActivity(String activity){
        setIsiStringSharedPreferences(activityKey, activity );
    }

    public String getActivity(){
        return getStringSharedPreferences(activityKey);
    }

    public void setSignal(String signal){
        setIsiStringSharedPreferences(signalKey, signal );
    }

    public String getSignal(){
        return getStringSharedPreferences(signalKey);
    }

    public void setDuration(String duration){
        setIsiStringSharedPreferences(durationKey, duration );
    }

    public String getDuration(){
        return getStringSharedPreferences(durationKey);
    }







}
