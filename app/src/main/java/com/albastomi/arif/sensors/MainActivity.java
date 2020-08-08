package com.albastomi.arif.sensors;

import com.albastomi.arif.Service.ForegroundService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.albastomi.arif.Utils.SetSharedPreference;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String syncInterval,insertInterval,kodeProject,email,idUser,nama,gyro_x,gyro_y,gyro_z,acc_x,acc_y,acc_z,hrv;
    SetSharedPreference fSetSharedPreference;
    Button btn_stop;
    TextView txt_gyro_x,txt_gyro_y,txt_gyro_z,txt_acc_x,txt_acc_y,txt_acc_z,txt_hrv;
    List<Entry> lineEntries = new ArrayList<Entry>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fSetSharedPreference=new SetSharedPreference(this,true);

        syncInterval=fSetSharedPreference.getSyncInterval();
        insertInterval=fSetSharedPreference.getInsertInterval();
        kodeProject=fSetSharedPreference.getProjectId();
        email=fSetSharedPreference.getemailLogin();
        idUser=fSetSharedPreference.getidUser();
        nama=fSetSharedPreference.getnamaLogin();
        btn_stop=(Button)findViewById(R.id.btn_stop);
        startService();
        drawLineChart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mTaskListener, new IntentFilter("backgroundProcessCallBack"));

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopService();
                Intent activitystartIntent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(activitystartIntent);

            }
        });

    }


    private BroadcastReceiver mTaskListener = new BroadcastReceiver() {

        @Override
        public void onReceive(Context cxt, Intent intent) {

         gyro_x=  intent.getStringExtra("gyro_x");
         gyro_y=  intent.getStringExtra("gyro_y");
         gyro_z=  intent.getStringExtra("gyro_z");
         acc_x=  intent.getStringExtra("acc_x");
         acc_y=  intent.getStringExtra("acc_y");
         acc_z=  intent.getStringExtra("acc_z");
         hrv=  intent.getStringExtra("hrv");

        }
    };

    public void startService() {

        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service.....");
        ContextCompat.startForegroundService(this, serviceIntent);

    }

    public void stopService() {

        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);

    }

    private void drawLineChart() {

        LineChart lineChart = findViewById(R.id.lineChart);
        List<Entry> lineEntries = getDataSet();
        LineDataSet lineDataSet = new LineDataSet(lineEntries, getString(R.string.gyro_x));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.DKGRAY);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setText(getString(R.string.price_in_last_12_days));
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.setData(lineData);

    }

    private List<Entry> getDataSet() {

        lineEntries.add(new Entry(0, 1));
        lineEntries.add(new Entry(1, 2));
        lineEntries.add(new Entry(2, 3));
        lineEntries.add(new Entry(3, 4));
        lineEntries.add(new Entry(4, 2));
        lineEntries.add(new Entry(5, 3));
        lineEntries.add(new Entry(6, 1));
        lineEntries.add(new Entry(7, 5));
        lineEntries.add(new Entry(8, 7));
        lineEntries.add(new Entry(9, 6));
        lineEntries.add(new Entry(10, 4));
        lineEntries.add(new Entry(11, 5));
        return lineEntries;

    }

}
