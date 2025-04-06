package com.powerroutine.controllerData;


import com.powerroutine.dtd.LoginDtd;

public interface LoginCallback {
    void onSuccess(LoginDtd loginResponse);
    void onFailure(String errorMessage);
}