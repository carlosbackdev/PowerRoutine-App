package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.EjerciceCallBack;
import com.powerroutine.interfaces.RutineListCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EjerciceData {
    private ApiService apiService = RetrofitClient.getApiService();

    public void getEjercices(RutineListDtd rutineListDtd, final EjerciceCallBack callback){
        Call<EjerciceDTD> ejerciceResponse = apiService.getEjercices(rutineListDtd);
        ejerciceResponse.enqueue(new Callback<EjerciceDTD>(){
            @Override
            public void onResponse(Call<EjerciceDTD> call, Response<EjerciceDTD> response){
                if (response.isSuccessful()){
                    EjerciceDTD ejerciceDTD = new EjerciceDTD();
                    ejerciceDTD = response.body();
                    if (ejerciceDTD.getEjercices() != null){
                        callback.onSuccess(ejerciceDTD);
                    } else {
                        callback.onFailure("Error: " + ejerciceDTD.getRespuesta());
                    }
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<EjerciceDTD> call, Throwable t){
                callback.onFailure(t.toString());
                }

        });

    }

}
