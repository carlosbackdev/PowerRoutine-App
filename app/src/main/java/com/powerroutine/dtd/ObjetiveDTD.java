package com.powerroutine.dtd;

import com.powerroutine.model.ObjetiveModel;

import java.util.List;

public class ObjetiveDTD {
    private List<ObjetiveModel> objetives;
    private String respuesta;

    public List<ObjetiveModel> getObjetives() {
        return objetives;
    }

    public void setObjetives(List<ObjetiveModel> objetives) {
        this.objetives = objetives;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "ObjetiveDTD{" +
                "objetives=" + objetives +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
