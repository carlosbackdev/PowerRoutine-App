package com.powerroutine.interfaces;

public interface RegisterCallBack {
    void onSuccess(String response);
    void onFailure(String errorMessage);
}
