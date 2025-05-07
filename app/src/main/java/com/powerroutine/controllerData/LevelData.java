package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.LevelDTD;
import com.powerroutine.interfaces.LevelCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LevelData {
    private ApiService apiService= RetrofitClient.getApiService();
    private LevelDTD levelDTD=new LevelDTD();

    public void getAllLevels(final LevelCallBack callBack){
        Call<LevelDTD> levelResponse=apiService.getAllLevels();
        levelResponse.enqueue(new Callback<LevelDTD>() {
            @Override
            public void onResponse(Call<LevelDTD> call, Response<LevelDTD> response) {
                if(response.isSuccessful()){
                    levelDTD=response.body();
                    callBack.onSuccess(levelDTD);
                }else {
                    callBack.onFailure("Error: " + levelDTD.getRespuesta());
                }
            }

            @Override
            public void onFailure(Call<LevelDTD> call, Throwable t) {
                callBack.onFailure(t.toString());
            }
        });
    }

}
