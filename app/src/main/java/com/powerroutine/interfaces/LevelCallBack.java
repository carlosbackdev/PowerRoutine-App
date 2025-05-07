package com.powerroutine.interfaces;

import com.powerroutine.dtd.LevelDTD;

public interface LevelCallBack {
    void onSuccess(LevelDTD levelresponse);
    void onFailure(String error);
}
