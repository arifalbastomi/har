package com.albastomi.arif.Apihelper;

import com.albastomi.arif.Model.LoginResult;
import com.albastomi.arif.Model.LoginData;
import com.albastomi.arif.Model.ProjectData;
import com.albastomi.arif.Model.ProjectResult;
import com.albastomi.arif.Model.SensorData;
import com.albastomi.arif.Model.SensorResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Body;

public interface UserService {

    @POST("v1/users/login")
    Call<LoginResult> getStringScalar(@Body LoginData body);

    @POST("v1/project/getproject")
    Call<ProjectResult> getStringScalar(@Body ProjectData body);

    @POST("v1/sensor/setdata")
    Call<SensorResult> getStringScalar(@Body SensorData body);
}
