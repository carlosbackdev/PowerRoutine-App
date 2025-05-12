package com.powerroutine.controllerData;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.UserCompletesDTD;
import com.powerroutine.interfaces.UserCompletesCallBack;
import com.powerroutine.interfaces.UserCompletesSaveCallBack;
import com.powerroutine.model.UserCompletesModel;
import com.powerroutine.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCompletesData {
    private ApiService apiService = RetrofitClient.getApiService();

    public void getUserCompletes(UserModel user, final UserCompletesCallBack callback) {
        Call<UserCompletesDTD> userCompletesResponse = apiService.getUserCompletes(user);
        userCompletesResponse.enqueue(new Callback<UserCompletesDTD>() {
            @Override
            public void onResponse(Call<UserCompletesDTD> call, Response<UserCompletesDTD> response) {
                if (response.isSuccessful()) {
                    UserCompletesDTD userCompletes = response.body();
                    System.out.println("RESPUESTA: " + response.body());
                    callback.onSuccess(userCompletes);
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserCompletesDTD> call, Throwable t) {
                callback.onFailure(t.toString());
            }
        });
    }

    public void saveUserCompletes(UserCompletesModel userCompletesModel, final UserCompletesSaveCallBack callback){
        Call<Boolean> userCompletesResponse = apiService.saveUserComplete(userCompletesModel);
        userCompletesResponse.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(false);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onFailure(false);
            }
        });
    }

}
