package com.albastomi.arif.sensors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import com.albastomi.arif.Utils.SetSharedPreference;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{

    Sensor accelerometer,gyroscope,hr;
    SensorManager sm;
    TextView acceleration,gyro,hrtext;
    String SQLiteQuery,syncInterval,insertInterval,kodeProject,email,idUser,nama,gyro_x="",gyro_y="",gyro_z="",acc_x="",acc_y="",acc_z="",hrv="";
    SQLiteDatabase sqLiteDatabase;
    SetSharedPreference fSetSharedPreference;
    Handler vHandler;
    Button btn_stop;

    Runnable vRunable=new Runnable() {
        @Override
        public void run() {
            insert_data();
            setTimer();
        }
    };

    private void setTimer(){
        if(vHandler==null){
            vHandler=new Handler();
        }
        stopTimer();
        vHandler.postDelayed(vRunable,Integer.parseInt(insertInterval));
    }

    private void stopTimer(){
        try {
            vHandler.removeCallbacks(vRunable);
        }catch (Exception e){

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fSetSharedPreference=new SetSharedPreference(this);

        syncInterval=fSetSharedPreference.getSyncInterval();
        insertInterval=fSetSharedPreference.getInsertInterval();
        kodeProject=fSetSharedPreference.getProjectId();
        email=fSetSharedPreference.getemailLogin();
        idUser=fSetSharedPreference.getidUser();
        nama=fSetSharedPreference.getnamaLogin();

        sqLiteDatabase = openOrCreateDatabase("HAR", Context.MODE_PRIVATE, null);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acceleration=(TextView) findViewById(R.id.acceleration);
        btn_stop=(Button)findViewById(R.id.btn_stop);
        if(accelerometer != null){
            sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            acceleration.setText("device tidak support accelerometer");
        }

        gyroscope= sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gyro=(TextView) findViewById(R.id.gyro);
        if(gyroscope != null){
            sm.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            gyro.setText("device tidak support gyroscope");
        }

        hr= sm.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        hrtext=(TextView) findViewById(R.id.hr);
        if(hr != null){
            sm.registerListener(this,hr,SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            hrtext.setText("device tidak support heart rate");
        }

        if(accelerometer != null || gyroscope != null || hr != null){
            setTimer();
        }

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

            if (event.sensor == accelerometer){

                acc_x=event.values[0]+"";
                acc_y=event.values[1]+"";
                acc_z=event.values[2]+"";
                acceleration.setText("\nX acc:"+event.values[0]+
                        "\nY acc:"+event.values[1]+
                        "\nZ acc:"+event.values[2]);

            }else if(event.sensor == gyroscope){

                gyro_x=event.values[0]+"";
                gyro_y=event.values[1]+"";
                gyro_z=event.values[2]+"";
                gyro.setText("\nX acc:"+event.values[0]+
                        "\nY acc:"+event.values[1]+
                        "\nZ acc:"+event.values[2]);

            }else if(event.sensor == hr){
                hrv=event.values[0]+"";
                hrtext.setText("\nHeart Rate:"+event.values[0]);
            }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void insert_data(){

        //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data_raw (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,id_user INTEGER,id_project INTEGER ,gyro_x VARCHAR,gyro_y VARCHAR,gyro_z VARCHAR,acc_x VARCHAR,acc_y VARCHAR,acc_z VARCHAR,hrv VARCHAR,createdate DATETIME);");
        SQLiteQuery = "INSERT INTO data_raw (id_user,id_project,gyro_x,gyro_y,gyro_z,acc_x,acc_y,acc_z,hrv,createdate) VALUES ('" + Integer.parseInt(idUser) + "','" + Integer.parseInt(kodeProject) + "','" + gyro_x + "', '" + gyro_y + "','" + gyro_z + "','" + acc_x + "','" + acc_y + "','" + acc_z + "','',strftime('%Y-%m-%d %H:%M:%f', 'now', 'localtime'));";
        sqLiteDatabase.execSQL(SQLiteQuery);

    }
}
