package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.MuscleDTD;
import com.powerroutine.interfaces.MuscleCallBack;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuscleData {
    private ApiService apiService = RetrofitClient.getApiService();
    private MuscleDTD muscleDTD= new MuscleDTD();

    public void getAllMuscles(final MuscleCallBack callback){
        Call<MuscleDTD> muscleResponse = apiService.getMuscles();
        muscleResponse.enqueue(new Callback<MuscleDTD>(){
            @Override
            public void onResponse(Call<MuscleDTD> call, Response<MuscleDTD> response){
                if(response.isSuccessful()){
                    muscleDTD = response.body();
                    if(muscleDTD.getMuscles() != null){
                        callback.onSuccess(muscleDTD);
                    }else{
                        callback.onFailure("Error: " +muscleDTD.getRespuesta());
                    }
                }else {
                    callback.onFailure("Error: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<MuscleDTD> call, Throwable t){
                callback.onFailure(t.toString());
            }
        });
    }


}
