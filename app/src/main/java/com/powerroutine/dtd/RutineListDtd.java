package com.powerroutine.dtd;

import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;

public class RutineListDtd {
    private ArrayList<RutineModel> rutinas;
    private String respuesta;
    private UserModel userModel;

    public RutineListDtd(ArrayList<RutineModel> rutinas, String respuesta) {
        this.rutinas = rutinas;
        this.respuesta = respuesta;
    }
    public RutineListDtd(ArrayList<RutineModel> rutinas, UserModel user) {
        this.rutinas = rutinas;
        this.userModel = user;
    }

    public UserModel getUser() {
        return userModel;
    }

    public void setUser(UserModel user) {
        this.userModel = user;
    }

    public RutineListDtd() {
        this.rutinas = new ArrayList<>();
    }

    public ArrayList<RutineModel> getRutinas() {
        return rutinas;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRutinas(ArrayList<RutineModel> rutinas) {
        this.rutinas = rutinas;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "RutineListDtd{" +
                "rutinas=" + rutinas +
                ", respuesta='" + respuesta + '\'' +
                ", user=" + userModel +
                '}';
    }
}