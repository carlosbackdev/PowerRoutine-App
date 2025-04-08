package com.powerroutine.controllerData;

import android.util.Log;

import com.google.gson.Gson;
import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.model.UserModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserData {

    public void login(UserModel user, final LoginCallback callback) {
        // Obtener la instancia de la API
        ApiService apiService = RetrofitClient.getApiService();

        // Hacer la llamada POST
        Call<LoginDtd> call = apiService.login(user);

        // Enviar la solicitud de manera asíncrona
        call.enqueue(new Callback<LoginDtd>() {
            @Override
            public void onResponse(Call<LoginDtd> call, Response<LoginDtd> response) {
                if (response.isSuccessful()) {
                    // Si la respuesta es exitosa, obtenemos el LoginDtd
                    LoginDtd loginResponse = response.body();
                    System.out.println("respuesta:" +response.body());
                    if (loginResponse != null) {
                        // Llamamos al callback con el resultado exitoso
                        callback.onSuccess(loginResponse);
                    } else {
                        // Si la respuesta no tiene un cuerpo válid
                        callback.onFailure("Error: Response body is null");
                    }
                } else {
                    Log.e("LoginError", "Response code: " + response.code());
                    Log.e("LoginError", "Response body: " + response.errorBody());

                    try {
                        String errorJson = response.errorBody().string();
                        Log.e("LoginError", "Error Response: " + errorJson);

                        Gson gson = new Gson();
                        LoginDtd respuesta = gson.fromJson(errorJson, LoginDtd.class);

                        if (respuesta != null) {
                            callback.onFailure(respuesta.getRespuesta());
                        } else {
                            callback.onFailure("Error: Unable to parse error response");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onFailure("Error: Unable to parse error response");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginDtd> call, Throwable t) {
                // Si hay un error en la red o cualquier otro problema
                Log.e("LoginError", "Error: " + t.getMessage());
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }

}
