package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.ObjetiveDTD;
import com.powerroutine.interfaces.ObjetiveCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjetiveData {
    private ApiService apiService= RetrofitClient.getApiService();
    private ObjetiveDTD objetiveDTD=new ObjetiveDTD();
    public void getAllObjetvies(final ObjetiveCallBack callback){
        Call<ObjetiveDTD> objetiveResponse = apiService.getAllObjetives();
        objetiveResponse.enqueue(new Callback<ObjetiveDTD>(){
            @Override
            public void onResponse(Call<ObjetiveDTD> call, Response<ObjetiveDTD> response){
                if (response.isSuccessful()){
                    objetiveDTD = response.body();
                    callback.onSuccess(objetiveDTD);
                }else{
                    callback.onFailure("Error: " +objetiveDTD.getRespuesta());
                }
            }
            @Override
            public void onFailure(Call<ObjetiveDTD> call, Throwable t){
                callback.onFailure(t.toString());
            }
        });
    }
}
