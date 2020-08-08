package com.albastomi.arif.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.albastomi.arif.Apihelper.ApiClient;
import com.albastomi.arif.Apihelper.UserService;
import com.albastomi.arif.Model.ActivityData;
import com.albastomi.arif.Model.ActivityResult;
import com.albastomi.arif.Model.LoginData;
import com.albastomi.arif.Model.LoginResult;
import com.albastomi.arif.Utils.SetSharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    EditText edt_activity;
    Button btn_save;
    UserService mApiInterface;
    SetSharedPreference fSetSharedPreference;
    String kodeProject,idUser,attempt;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        edt_activity=(EditText)findViewById(R.id.edt_activity);
        btn_save=(Button)findViewById(R.id.btn_save);

        fSetSharedPreference=new SetSharedPreference(this,true);
        kodeProject=fSetSharedPreference.getProjectId();
        idUser=fSetSharedPreference.getidUser();
        attempt=fSetSharedPreference.getAttempt();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mApiInterface = ApiClient.getClient().create(UserService.class);
                Call<ActivityResult> call=mApiInterface.getStringScalar(new ActivityData(Integer.parseInt(kodeProject),Integer.parseInt(idUser),edt_activity.getText().toString(),Integer.parseInt(attempt),"5e636b16-df7f-4a53-afbe-497e6fe07edc" ) );
                call.enqueue(new Callback<ActivityResult>() {
                    @Override
                    public void onResponse(Call<ActivityResult> call, Response<ActivityResult> response) {
                        //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                        if(response.body().getStatus_code()==200){

                            Intent activitystartIntent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(activitystartIntent);

                        }else {
                            //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                           /* String msg = response.body().getMessage();
                            int docId = response.body().getDoctorid();
                            boolean error = response.body().getError();

                            boolean activie = response.body().getActive()();*/
                            Toast.makeText(getBaseContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ActivityResult> call, Throwable t) {
                        //for getting error in network put here Toast, so get the error on network

                        Toast.makeText(getBaseContext(),"error network",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
