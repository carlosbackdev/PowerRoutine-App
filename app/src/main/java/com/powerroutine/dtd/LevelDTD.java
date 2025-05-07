package com.powerroutine.dtd;

import com.powerroutine.model.LevelRange;

import java.util.List;

public class LevelDTD {
    private List<LevelRange> levels;
    private String respuesta;

    @Override
    public String toString() {
        return "LevelDTD{" +
                "levels=" + levels +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

    public List<LevelRange> getLevels() {
        return levels;
    }

    public void setLevels(List<LevelRange> levels) {
        this.levels = levels;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
