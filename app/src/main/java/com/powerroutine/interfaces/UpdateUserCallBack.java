package com.powerroutine.interfaces;

import com.powerroutine.dtd.LoginDtd;

public interface UpdateUserCallBack {
    void onSuccess(LoginDtd loginResponse);
    void onFailure(String errorMessage);
}
