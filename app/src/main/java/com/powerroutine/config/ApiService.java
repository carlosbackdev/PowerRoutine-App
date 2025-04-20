package com.powerroutine.config;

import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.model.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/login")
    Call<LoginDtd> login(@Body UserModel user);
    @POST("user/register")
    Call<ResponseBody> resgister(@Body UserModel user);
    @POST("user/update")
    Call<LoginDtd> update(@Body UserModel user);
    @POST("rutine/getRutine")
    Call<RutineListDtd> getRutinesForDay(@Body UserModel user);

    @POST("rutine/saveRutineUser")
    Call<ResponseBody> setRutinesForUser(@Body RutineListDtd rutineListDtd);
}
