package com.powerroutine.interfaces;

import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.UserCompletesDTD;

public interface UserCompletesCallBack {
    void onSuccess(UserCompletesDTD userCompletesDTD);
    void onFailure(String errorMessage);
}
