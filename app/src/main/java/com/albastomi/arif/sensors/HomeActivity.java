package com.albastomi.arif.sensors;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.albastomi.arif.Utils.SetSharedPreference;

public class HomeActivity extends AppCompatActivity  {
    SetSharedPreference fSetSharedPreference;
    Button mulai,history,logout,delete;
    String SQLiteQuery;
    SQLiteDatabase sqLiteDatabase;
    ProgressDialog pDialog;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sqLiteDatabase = openOrCreateDatabase("HAR", Context.MODE_PRIVATE, null);
        fSetSharedPreference=new SetSharedPreference(this,true);
        mulai=(Button) findViewById(R.id.btn_mulai);

        history=(Button) findViewById(R.id.btn_history);

        delete=(Button) findViewById(R.id.btn_delete);

        logout=(Button) findViewById(R.id.btn_logout);

        mulai.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               //System.out.println("Button Clicked");
                Intent activitystartIntent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(activitystartIntent);
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //System.out.println("Button Clicked");
                Intent activityhistorytIntent = new Intent(getApplicationContext(), HistoryProjectActivity.class);
                startActivity(activityhistorytIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                SQLiteQuery = " Delete from data_raw; ";
                sqLiteDatabase.execSQL(SQLiteQuery);
               // SQLiteQuery = " Alter table  data_raw AUTO_INCREMENT=1";
               // sqLiteDatabase.execSQL(SQLiteQuery);
                closeDialog();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fSetSharedPreference.setLogin("0");
                finish();
            }
        });

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

}
