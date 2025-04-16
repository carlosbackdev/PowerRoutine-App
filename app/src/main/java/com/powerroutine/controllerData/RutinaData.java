package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.model.UserModel;

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
}
