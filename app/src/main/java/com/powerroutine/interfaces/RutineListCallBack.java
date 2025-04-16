package com.powerroutine.interfaces;

import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.dtd.RutineListDtd;

public interface RutineListCallBack {
    void onSuccess(RutineListDtd rutinesResponse);
    void onFailure(String errorMessage);
}
