package com.albastomi.arif.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.albastomi.arif.Apihelper.ApiClient;
import com.albastomi.arif.Apihelper.UserService;
import com.albastomi.arif.Model.LoginData;
import com.albastomi.arif.Model.LoginResult;
import com.albastomi.arif.Model.ProjectData;
import com.albastomi.arif.Model.ProjectResult;
import com.albastomi.arif.Utils.SetSharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StartActivity extends AppCompatActivity {

    private EditText edtProject,edtActivity,edtSignal,edtDurasi;
    UserService mApiInterface;
    private Button btnMulai;
    SetSharedPreference fSetSharedPreference;
    String idUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        edtProject  = (EditText) findViewById(R.id.edt_project);
        edtSignal  = (EditText) findViewById(R.id.edt_signal);
        edtDurasi  = (EditText) findViewById(R.id.edt_durasi);
        edtActivity  = (EditText) findViewById(R.id.edt_activity);
        btnMulai  = (Button) findViewById(R.id.btn_mulai);
        fSetSharedPreference=new SetSharedPreference(this,true);
        idUser=fSetSharedPreference.getidUser();

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mApiInterface = ApiClient.getClient().create(UserService.class);
                Call<ProjectResult> call=mApiInterface.getStringScalar(new ProjectData(edtProject.getText().toString(),idUser,"5e636b16-df7f-4a53-afbe-497e6fe07edc" ) );
                call.enqueue(new Callback<ProjectResult>() {
                    @Override
                    public void onResponse(Call<ProjectResult> call, Response<ProjectResult> response) {
                        //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                        if(response.body().getStatus_code()==200){

                            fSetSharedPreference.setProjectid(response.body().getId_project()+"");
                            fSetSharedPreference.setActivity(edtActivity.getText().toString());
                            fSetSharedPreference.setSignal(edtSignal.getText().toString()+"");
                            fSetSharedPreference.setDuration(edtDurasi.getText().toString()+"");
                            fSetSharedPreference.setSyncInterval(response.body().getSync_interval()+"");
                            fSetSharedPreference.setInsertInterval(response.body().getInsert_interval()+"");
                            fSetSharedPreference.setAttempt(response.body().getAttempt()+"");
                            fSetSharedPreference.setAktif("1");
                            Intent activitystartIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(activitystartIntent);
                            finish();

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
                    public void onFailure(Call<ProjectResult> call, Throwable t) {
                        //for getting error in network put here Toast, so get the error on network

                        Toast.makeText(getBaseContext(),"error network",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

}
