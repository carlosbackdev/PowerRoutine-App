package com.powerroutine.config;

import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/login")
    Call<LoginDtd> login(@Body UserModel user);
}
