package com.powerroutine.controllerData;

import android.util.Log;

import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.model.UserModel;

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
                    if (loginResponse != null) {
                        // Llamamos al callback con el resultado exitoso
                        callback.onSuccess(loginResponse);
                    } else {
                        // Si la respuesta no tiene un cuerpo válido
                        callback.onFailure("Error: Response body is null");
                    }
                } else {
                    // Si la respuesta no es exitosa, se maneja aquí
                    Log.e("LoginError", "Response code: " + response.code());
                    Log.e("LoginError", "Response message: " + response.message());
                    Log.e("LoginError", "Response body: " + response.errorBody());
                    callback.onFailure("Error: " + response.message());
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
