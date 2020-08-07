package com.albastomi.arif;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.content.Context;

public class HarApplication extends Application {

    //String SQLiteQuery;
    SQLiteDatabase sqLiteDatabase;
    @Override
    public void onCreate() {

        super.onCreate();

        sqLiteDatabase = openOrCreateDatabase("HAR", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data_raw (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,id_user INTEGER,id_project INTEGER ,gyro_x VARCHAR,gyro_y VARCHAR,gyro_z VARCHAR,acc_x VARCHAR,acc_y VARCHAR,acc_z VARCHAR,hrv VARCHAR,createdate DATETIME);");

    }
}
