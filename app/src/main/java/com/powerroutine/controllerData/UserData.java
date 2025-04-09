package com.powerroutine.controllerData;

import android.util.Log;

import com.google.gson.Gson;
import com.powerroutine.config.ApiService;
import com.powerroutine.config.RetrofitClient;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.interfaces.LoginCallback;
import com.powerroutine.interfaces.RegisterCallBack;
import com.powerroutine.model.UserModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserData {
    private ApiService apiService=RetrofitClient.getApiService();

    public void login(UserModel user, final LoginCallback callback) {

        // Hacer la llamada POST
        Call<LoginDtd> Logincall = apiService.login(user);

        // Enviar la solicitud de manera asíncrona
        Logincall.enqueue(new Callback<LoginDtd>() {
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
                }
                if (!response.isSuccessful()){
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

    public void register(UserModel user, final RegisterCallBack callback){
    Call <ResponseBody> registerCall=apiService.resgister(user);

    registerCall.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response.isSuccessful()){
                String respuesta= null;
                try {
                    respuesta = response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(response);
            System.out.println("Respuesta del servidor: w2" +respuesta);
                if (respuesta!=null){
                    callback.onSuccess(respuesta);
                }else {
                    // Si la respuesta no tiene un cuerpo válid
                    callback.onFailure(respuesta);
                    System.out.println(response.body());
                    System.out.println(response.code());
                    System.out.println(response.errorBody());
                    System.out.println(response.message());
                    System.out.println(response);
                }
            }

            if (!response.isSuccessful()){

                try { String errorJson = response.errorBody().string();
                    callback.onFailure(errorJson);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFailure("Error al leer el error");
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            callback.onFailure("error en la llamada"+t.getMessage());
        }
    });

    }

}
