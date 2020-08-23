package com.albastomi.arif.Service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.albastomi.arif.Apihelper.ApiClient;
import com.albastomi.arif.Apihelper.UserService;
import com.albastomi.arif.Model.SensorData;
import com.albastomi.arif.Model.SensorResult;
import com.albastomi.arif.Utils.SetSharedPreference;
import com.albastomi.arif.sensors.R;
import com.albastomi.arif.sensors.MainActivity;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForegroundService extends Service implements SensorEventListener  {

    Sensor accelerometer,gyroscope,hr;
    SensorManager sm;
    String gyro_x="",gyro_y="",gyro_z="",acc_x="",acc_y="",acc_z="",hrv="";
    String SQLiteQuery,syncInterval,insertInterval,kodeProject,email,idUser,nama,attempt,activity,signal;
    SQLiteDatabase sqLiteDatabase;
    SetSharedPreference fSetSharedPreference;
    Handler vHandler;
    UserService mApiInterface;

    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    Runnable vRunable=new Runnable() {
        @Override
        public void run() {
            Log.e("FOREGROUND:","insert to local");
            insert_data();
            setTimer();
        }
    };

    Runnable vSync= new Runnable() {
        @Override
        public void run() {
            Log.e("FOREGROUND:","sync to server");
            sync();
            setTimerSync();
        }
    };

    Runnable vBroadcast= new Runnable() {
        @Override
        public void run() {
            Log.e("FOREGROUND:","broadcast to server");
            broadcast();
            setTimerBroadcast();
        }
    };

    private void setTimerBroadcast(){
        if(vHandler==null){
            vHandler=new Handler();
        }
        stopTimerBroadcast();
        vHandler.postDelayed(vBroadcast,1000);
    }

    private void setTimerSync(){
        if(vHandler==null){
            vHandler=new Handler();
        }
        stopTimerSync();
        vHandler.postDelayed(vSync,Integer.parseInt(syncInterval));
    }

    private void setTimer(){
        if(vHandler==null){
            vHandler=new Handler();
        }
        stopTimer();
        vHandler.postDelayed(vRunable,Integer.parseInt(signal)/1000);
    }



    private void stopTimer(){
        try {
            vHandler.removeCallbacks(vRunable);
        }catch (Exception e){

        }

    }

    private void stopTimerBroadcast(){
        try {
            vHandler.removeCallbacks(vBroadcast);
        }catch (Exception e){

        }

    }

    private void stopTimerSync(){
        try {
            vHandler.removeCallbacks(vSync);
        }catch (Exception e){

        }

    }


    @Override
    public void onCreate() {
        super.onCreate();

        fSetSharedPreference=new SetSharedPreference(this,false);

        syncInterval=fSetSharedPreference.getSyncInterval();
        insertInterval=fSetSharedPreference.getInsertInterval();
        kodeProject=fSetSharedPreference.getProjectId();
        email=fSetSharedPreference.getemailLogin();
        idUser=fSetSharedPreference.getidUser();
        nama=fSetSharedPreference.getnamaLogin();
        attempt=fSetSharedPreference.getAttempt();
        activity=fSetSharedPreference.getActivity();
        signal=fSetSharedPreference.getSignal();

        sqLiteDatabase = openOrCreateDatabase("HAR", Context.MODE_PRIVATE, null);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //SensorManager.SENSOR_DELAY_NORMAL
        if(accelerometer != null){
            sm.registerListener(this,accelerometer,Integer.parseInt(signal));

        }else{
            //acceleration.setText("device tidak support accelerometer");
        }

        gyroscope= sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscope != null){
            sm.registerListener(this,gyroscope,Integer.parseInt(signal));

        }else{
            //.setText("device tidak support gyroscope");
        }

        hr= sm.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        //hrtext=(TextView) findViewById(R.id.hr);
        if(hr != null){
            sm.registerListener(this,hr,Integer.parseInt(signal));

        }else{
           // hrtext.setText("device tidak support heart rate");
        }

        if(accelerometer != null || gyroscope != null || hr != null){
            setTimer();
            //setTimerSync();
            setTimerBroadcast();

        }

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if((intent.getStringExtra("aksi") == null ? "" : intent.getStringExtra("aksi") ).equals("stopservice")){
            stopForeground(true);
            stopTimer();
            stopTimerSync();
            stopTimerBroadcast();
            stopSelf();

        }else {
            String input = intent.getStringExtra("inputExtra");
            createNotificationChannel();
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, 0);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Foreground Service for HAR")
                    .setContentText(input)
                    .setSmallIcon(R.drawable.user_icon)
                    .setContentIntent(pendingIntent)
                    .build();
            startForeground(1, notification);
        }
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopService();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor == accelerometer){

            acc_x=event.values[0]+"";
            acc_y=event.values[1]+"";
            acc_z=event.values[2]+"";

        }else if(event.sensor == gyroscope){

            gyro_x=event.values[0]+"";
            gyro_y=event.values[1]+"";
            gyro_z=event.values[2]+"";

        }else if(event.sensor == hr){
            hrv=event.values[0]+"";

        }

        //insert_data();

        //if(event.sensor == accelerometer || event.sensor == gyroscope || event.sensor == hr){
        //}

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void broadcast(){

        Intent tn = new Intent("backgroundProcessCallBack");
        tn.putExtra("acc_x",acc_x);
        tn.putExtra("acc_y",acc_y);
        tn.putExtra("acc_z",acc_z);
        tn.putExtra("gyro_x",gyro_x);
        tn.putExtra("gyro_y",gyro_y);
        tn.putExtra("gyro_z",gyro_z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(tn);

    }

    private void insert_data(){

        if(!gyro_x.equals("0") || !gyro_y.equals("0") || !gyro_z.equals("0") || !gyro_x.equals("") || !gyro_y.equals("") || !gyro_z.equals("")){
            //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data_raw (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,id_user INTEGER,id_project INTEGER ,gyro_x VARCHAR,gyro_y VARCHAR,gyro_z VARCHAR,acc_x VARCHAR,acc_y VARCHAR,acc_z VARCHAR,hrv VARCHAR,createdate DATETIME);");
            Log.e("FOREGROUND ","insert");
            SQLiteQuery = "INSERT INTO data_raw (id_user,id_project,gyro_x,gyro_y,gyro_z,acc_x,acc_y,acc_z,hrv,createdate) VALUES ('" + Integer.parseInt(idUser) + "','" + Integer.parseInt(kodeProject) + "','" + gyro_x + "', '" + gyro_y + "','" + gyro_z + "','" + acc_x + "','" + acc_y + "','" + acc_z + "','',strftime('%Y-%m-%d %H:%M:%f', 'now', 'localtime'));";
            sqLiteDatabase.execSQL(SQLiteQuery);
        }

    }

    private void sync(){

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
                        Log.e("FOREGROUND failed ",response.body().getMessage());
                        //Toast.makeText(getBaseContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SensorResult> call, Throwable t) {
                    //for getting error in network put here Toast, so get the error on network
                    Log.e("FOREGROUND failed ","error network");
                   // Toast.makeText(getBaseContext(),"error network",Toast.LENGTH_SHORT).show();
                }
            });

            res.moveToNext();
        }
    }
}
