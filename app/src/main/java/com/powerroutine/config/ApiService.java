package com.powerroutine.config;

import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.dtd.MuscleDTD;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.List;

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

    @POST("rutine/getRutineUser")
    Call<RutineListDtd> getRutinesForUser(@Body UserModel user);

    @POST("ejercice/getEjercices")
    Call<EjerciceDTD> getEjercices(@Body RutineListDtd rutineListDtd);

    @POST("rutine/updateRutineUser")
    Call<ResponseBody> updateRutineUser(@Body RutineModel rutineModel);

    @POST("body/getBodys")
    Call<BodyDTD> getBodys(@Body List<Integer> ids);

    @GET("muscle/getMuscles")
    Call<MuscleDTD> getMuscles();

    @GET("body/getAllBodys")
    Call<BodyDTD> getAllBodys();

    @GET("ejercice/getAllEjercices")
    Call<EjerciceDTD> getAllEjercices();
}
