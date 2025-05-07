package com.powerroutine.dtd;

import com.powerroutine.model.MuscleModel;

import java.util.List;

public class MuscleDTD {
    private List<MuscleModel> muscles;
    private String respuesta;

    public List<MuscleModel> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<MuscleModel> muscles) {
        this.muscles = muscles;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "MuscleDTD{" +
                "muscles=" + muscles +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
