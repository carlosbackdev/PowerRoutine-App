package com.powerroutine.interfaces;

import com.powerroutine.dtd.UserCompletesDTD;

public interface UserCompletesSaveCallBack {
    void onSuccess(boolean response);
    void onFailure(boolean response);
}
