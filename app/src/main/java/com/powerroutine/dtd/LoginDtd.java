package com.powerroutine.dtd;

import com.powerroutine.model.UserModel;

public class LoginDtd {
    private UserModel userModel;
    private String respuesta;

    public UserModel getUserModel() {
        return userModel;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "LoginDtd{" +
                "userModel=" + userModel +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

}
