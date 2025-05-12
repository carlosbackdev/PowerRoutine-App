package com.powerroutine.dtd;

import com.powerroutine.model.UserCompletesModel;

import java.util.ArrayList;
import java.util.List;

public class UserCompletesDTD {
    private List<UserCompletesModel> userCompletesModel;
    private String respuesta;

    @Override
    public String toString() {
        return "UserCompletesDTD{" +
                "userCompletesModel=" + userCompletesModel +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

    public void addItem(UserCompletesModel userCompletesModel) {
        if(this.userCompletesModel == null) {
            this.userCompletesModel = new ArrayList<>();
        }
        this.userCompletesModel.add(userCompletesModel);
    }

    public List<UserCompletesModel> getUserCompletesModel() {
        return userCompletesModel;
    }

    public void setUserCompletesModel(List<UserCompletesModel> userCompletesModel) {
        this.userCompletesModel = userCompletesModel;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
