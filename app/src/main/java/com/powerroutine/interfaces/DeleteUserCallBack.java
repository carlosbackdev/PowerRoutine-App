package com.powerroutine.interfaces;

public interface DeleteUserCallBack {
    void onSuccess(boolean response);

    void onFailure(String errorMessage);
}
