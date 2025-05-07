package com.powerroutine.dtd;

import com.powerroutine.model.BodyModel;

import java.util.ArrayList;
import java.util.List;

public class BodyDTD {
    private List<BodyModel> bodys;
    private String respuesta;

    public BodyDTD() {
        this.bodys = new ArrayList<>();
    }

    public List<BodyModel> getBodys() {
        return bodys;
    }

    public void setBodys(List<BodyModel> bodys) {
        this.bodys = bodys;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
