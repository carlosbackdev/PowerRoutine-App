package com.powerroutine.interfaces;

import com.powerroutine.dtd.ObjetiveDTD;

public interface ObjetiveCallBack {
    void onSuccess(ObjetiveDTD objetiveResponse);
    void onFailure(String errorMessage);
}
