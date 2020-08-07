package com.albastomi.arif.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.albastomi.arif.Model.LoginData;
import com.albastomi.arif.Model.LoginResult;
import com.albastomi.arif.Apihelper.ApiClient;
import com.albastomi.arif.Apihelper.UserService;
import com.albastomi.arif.Utils.SetSharedPreference;

import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText email,password;
    UserService mApiInterface;
    SetSharedPreference fSetSharedPreference;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email  = (EditText) findViewById(R.id.edt_username);
        password  = (EditText) findViewById(R.id.edt_password);
        btnLogin  = (Button) findViewById(R.id.btn_login);

        fSetSharedPreference=new SetSharedPreference(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiInterface = ApiClient.getClient().create(UserService.class);
                Call<LoginResult> call=mApiInterface.getStringScalar(new LoginData(email.getText().toString(),password.getText().toString(),"5e636b16-df7f-4a53-afbe-497e6fe07edc" ) );
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        //response.body() have your LoginResult fields and methods  (example you have to access error then try like this response.body().getError() )
                        if(response.body().getStatus_code()==200){

                            //Toast.makeText(getBaseContext(),response.body().getEmail(),Toast.LENGTH_SHORT).show();
                            fSetSharedPreference.setemailLogin(response.body().getEmail());
                            fSetSharedPreference.setnamaLogin(response.body().getNama());
                            fSetSharedPreference.setidUser(response.body().getId_user()+"");
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
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        //for getting error in network put here Toast, so get the error on network

                        Toast.makeText(getBaseContext(),"error network",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
