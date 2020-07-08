package com.albastomi.arif.sensors;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{

    Sensor accelerometer,gyroscope,hr;
    SensorManager sm;
    TextView acceleration,gyro,hrtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acceleration=(TextView) findViewById(R.id.acceleration);
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

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            if (event.sensor == accelerometer){
                acceleration.setText("\nX acc:"+event.values[0]+
                        "\nY acc:"+event.values[1]+
                        "\nZ acc:"+event.values[2]);
            }else if(event.sensor == gyroscope){
                gyro.setText("\nX acc:"+event.values[0]+
                        "\nY acc:"+event.values[1]+
                        "\nZ acc:"+event.values[2]);
            }else if(event.sensor == hr){
                hrtext.setText("\nHeart Rate:"+event.values[0]);
            }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
