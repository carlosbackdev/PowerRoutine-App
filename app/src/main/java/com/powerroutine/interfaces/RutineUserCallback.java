package com.powerroutine.interfaces;

import com.powerroutine.dtd.RutineListDtd;

public interface RutineUserCallback {
    void onSuccess(String response);
    void onFailure(String errorMessage);
}
