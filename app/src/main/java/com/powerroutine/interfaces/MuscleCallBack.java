package com.powerroutine.interfaces;

import com.powerroutine.dtd.MuscleDTD;

public interface MuscleCallBack {
    void onSuccess(MuscleDTD muscleResponse);
    void onFailure(String errorMessage);
}
