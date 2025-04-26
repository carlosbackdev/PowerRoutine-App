package com.powerroutine.interfaces;

import com.powerroutine.controllerData.EjerciceData;
import com.powerroutine.dtd.EjerciceDTD;

public interface EjerciceCallBack {
   void onSuccess(EjerciceDTD ejerciceResponse);
   void onFailure(String errorMessage);
}
