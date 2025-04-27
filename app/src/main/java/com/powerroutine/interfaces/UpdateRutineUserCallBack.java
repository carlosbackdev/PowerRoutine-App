package com.powerroutine.interfaces;

import com.powerroutine.dtd.LoginDtd;

import okhttp3.ResponseBody;

public interface UpdateRutineUserCallBack {
        void onSuccess(ResponseBody responseBody);
        void onFailure(String errorMessage);
}
