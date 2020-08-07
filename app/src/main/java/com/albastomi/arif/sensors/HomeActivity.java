package com.albastomi.arif.sensors;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class HomeActivity extends AppCompatActivity  {

    Button mulai,history;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mulai=(Button) findViewById(R.id.btn_mulai);

        history=(Button) findViewById(R.id.btn_history);

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

    }

}
