package com.powerroutine.interfaces;

import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.EjerciceDTD;

public interface BodyCallBack {
    void onSuccess(BodyDTD bodyResponse);
    void onFailure(String errorMessage);
}
