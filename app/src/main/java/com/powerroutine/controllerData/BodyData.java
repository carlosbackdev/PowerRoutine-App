package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.interfaces.BodyCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BodyData {
    private ApiService apiService = RetrofitClient.getApiService();
    private BodyDTD bodyDTD;

    public void getBodys(List<Integer> ids, final BodyCallBack callback){
        Call<BodyDTD> bodyResponse= apiService.getBodys(ids);
        bodyResponse.enqueue(new Callback<BodyDTD>() {
            @Override
            public void onResponse(Call<BodyDTD> call, Response<BodyDTD> response) {
                if(response.isSuccessful()){
                    bodyDTD=response.body();
                    if (bodyDTD.getBodys() != null){
                        callback.onSuccess(bodyDTD);
                    } else {
                        callback.onFailure("Error: " + bodyDTD.getRespuesta());
                    }
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BodyDTD> call, Throwable t) {
                callback.onFailure(t.toString());
            }
        });
    }

}
