package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.interfaces.RutineUserCallback;
import com.powerroutine.model.UserModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutinaData {
    private ApiService apiService= RetrofitClient.getApiService();

    public void getRutinesForDay(UserModel user, final RutineListCallBack callback){
        Call<RutineListDtd> rutineResponse=apiService.getRutinesForDay(user);
        rutineResponse.enqueue(new Callback<RutineListDtd>() {
            @Override
            public void onResponse(Call<RutineListDtd> call, Response<RutineListDtd> response) {
                if (response.isSuccessful()) {
                    RutineListDtd rutineListResponse = response.body();
                    if (rutineListResponse.getRutinas() != null) {
                        callback.onSuccess(rutineListResponse);
                    } else {
                        callback.onFailure(rutineListResponse.getRespuesta());
                    }
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RutineListDtd> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });

    }
    public void saveRutineUser(RutineListDtd rutineListDtd, final RutineUserCallback callback){
        Call<ResponseBody> rutineResponse=apiService.setRutinesForUser(rutineListDtd);
        rutineResponse.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String responseBody= null;
                    try {
                        responseBody = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (responseBody != null) {
                        callback.onSuccess(responseBody);
                    } else {
                        callback.onFailure("Error: " + response.code());
                    }
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof retrofit2.HttpException) {
                    retrofit2.HttpException httpException = (retrofit2.HttpException) t;
                    try {
                        // Recuperar el cuerpo de la respuesta de error
                        String errorBody = httpException.response().errorBody().string();
                        callback.onFailure(errorBody);
                    } catch (Exception e) {
                        callback.onFailure("Error desconocido: " + e.getMessage());
                    }
                } else {
                    callback.onFailure("Error: " + t.getMessage());
                }
            }
        });
    }
}
