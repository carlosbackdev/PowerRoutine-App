package com.powerroutine.dtd;

import com.powerroutine.model.EjerciceModel;

import java.util.ArrayList;

public class EjerciceDTD {
    private ArrayList<EjerciceModel> ejercices;
    private String respuesta;

    public EjerciceDTD() {
        ejercices=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "EjerciceDTD{" +
                "ejercices=" + ejercices +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

    public ArrayList<EjerciceModel> getEjercices() {
        return ejercices;
    }

    public void setEjercices(ArrayList<EjerciceModel> ejercices) {
        this.ejercices = ejercices;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
