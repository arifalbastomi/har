package com.albastomi.arif.sensors;

import com.albastomi.arif.Apihelper.ApiClient;
import com.albastomi.arif.Apihelper.UserService;
import com.albastomi.arif.Model.SensorData;
import com.albastomi.arif.Model.SensorResult;
import com.albastomi.arif.Service.ForegroundService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;
import android.os.CountDownTimer;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String SQLiteQuery,syncInterval,insertInterval,kodeProject,email,idUser,nama,gyro_x,gyro_y,gyro_z,acc_x,acc_y,acc_z,hrv,attempt,activity,durasi;
    SetSharedPreference fSetSharedPreference;
    Button btn_stop,btn_sync;
    TextView txt_gyro_x,txt_gyro_y,txt_gyro_z,txt_acc_x,txt_acc_y,txt_acc_z,txt_hrv;
    SQLiteDatabase sqLiteDatabase;
    UserService mApiInterface;
    ProgressDialog pDialog;
    List<Entry> lineEntries = new ArrayList<Entry>();
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fSetSharedPreference=new SetSharedPreference(this,true);
        sqLiteDatabase = openOrCreateDatabase("HAR", Context.MODE_PRIVATE, null);
        syncInterval=fSetSharedPreference.getSyncInterval();
        insertInterval=fSetSharedPreference.getInsertInterval();
        kodeProject=fSetSharedPreference.getProjectId();
        email=fSetSharedPreference.getemailLogin();
        idUser=fSetSharedPreference.getidUser();
        nama=fSetSharedPreference.getnamaLogin();
        attempt=fSetSharedPreference.getAttempt();
        activity=fSetSharedPreference.getActivity();
        durasi=fSetSharedPreference.getDuration();
        btn_stop=(Button)findViewById(R.id.btn_stop);
        btn_stop.setVisibility(View.GONE);
        btn_sync=(Button)findViewById(R.id.btn_sync);
        btn_sync.setText("Process..please wait");
        ctx=this;
       // startService();
        startLoading();
        //starttimer();
        drawLineChart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mTaskListener, new IntentFilter("backgroundProcessCallBack"));
        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                new ProgressDialogSync(ctx).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
               // sync();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopService();
                //sync();
                //closeDialog();
                fSetSharedPreference.setAktif("0");
                Intent activitystartIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(activitystartIntent);
                finish();

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
        serviceIntent.putExtra("aksi","stopservice");
        startService(serviceIntent);

    }
    public void startLoading(){
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.e("timerLoading", "(" + millisUntilFinished / 1000 +")");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                Log.e("timerLoading", "Done");
                startService();
                starttimer();
            }

        }.start();
    }
    public void starttimer(){
        new CountDownTimer(Integer.parseInt(durasi), 1000) {

            public void onTick(long millisUntilFinished) {
                Log.e("timerWait", "(" + millisUntilFinished / 1000 +")");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                Log.e("timerWai", "Done");
                stopService();
                btn_sync.setText("Sync");
                btn_stop.setVisibility(View.VISIBLE);
                //pDialog.dismiss();
               // FungsiDialog.pesan_peringatanCancelableFalse(ctx.getString(R.string.pesan_peringatan_jika_tidak_ada_kurir), false, ctx.getString(R.string.judul_warning), ctx);
            }

        }.start();
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

    private void sync(){
        //showDialog();
        String id,iduser,id_project,gyro_x_i,gyro_y_i,gyro_z_i,acc_x_i,acc_y_i,acc_z_i,hrv_i="",createdate;
        Cursor res = sqLiteDatabase.rawQuery( "select * from data_raw order by createdate", null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {

            id =res.getString(res.getColumnIndex("id"));
            iduser =res.getString(res.getColumnIndex("id_user"));
            id_project =res.getString(res.getColumnIndex("id_project"));
            gyro_x_i =res.getString(res.getColumnIndex("gyro_x"));
            gyro_y_i =res.getString(res.getColumnIndex("gyro_y"));
            gyro_z_i =res.getString(res.getColumnIndex("gyro_z"));
            acc_x_i =res.getString(res.getColumnIndex("acc_x"));
            acc_y_i =res.getString(res.getColumnIndex("acc_y"));
            acc_z_i =res.getString(res.getColumnIndex("acc_z"));
            createdate =res.getString(res.getColumnIndex("createdate"));

            Log.e("FOREGROUND getdata  :",id+" "+iduser+" "+ id_project+" "+ gyro_x_i +" "+ gyro_y_i +" "+ gyro_z_i +" "+acc_x_i+" "+acc_y_i +" "+acc_z_i +" "+ createdate);

            mApiInterface = ApiClient.getClient().create(UserService.class);
            //public SensorData(String id,String id_user,String id_project,String gyro_x,String gyro_y,String gyro_z,String acc_x,String acc_y,String acc_z,String hrv,String createdate,String authorization) {
            Call<SensorResult> call=mApiInterface.getStringScalar(new SensorData(id,iduser,id_project,gyro_x_i,gyro_y_i,gyro_z_i,acc_x_i,acc_y_i,acc_z_i,hrv_i,createdate,attempt,activity,"5e636b16-df7f-4a53-afbe-497e6fe07edc" ) );
            call.enqueue(new Callback<SensorResult>() {
                @Override
                public void onResponse(Call<SensorResult> call, Response<SensorResult> response) {
                    //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                    if(response.body().getStatus_code()==200){
                        //DELETE FROM table WHERE search_condition
                        SQLiteQuery = "Delete from data_raw where id= '"+ response.body().getId() +"' ";
                        sqLiteDatabase.execSQL(SQLiteQuery);
                    }else {
                       // closeDialog();
                        Log.e("FOREGROUND failed ",response.body().getMessage());
                        //Toast.makeText(getBaseContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SensorResult> call, Throwable t) {
                    //closeDialog();
                    //for getting error in network put here Toast, so get the error on network
                    Log.e("FOREGROUND failed ","error network");

                    //Toast.makeText(getBaseContext(),"error network",Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getBaseContext(),"error network",Toast.LENGTH_SHORT).show();
                }
            });

            res.moveToNext();
        }

       // closeDialog();
    }



    private void showDialog(){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void closeDialog(){
        pDialog.dismiss();
    }

    class ProgressDialogSync extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;
        Context ctx;
        public ProgressDialogSync(Context ctx){
            this.ctx=ctx;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ctx);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Sync to server please wait...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            sync();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

        }
    }

}
