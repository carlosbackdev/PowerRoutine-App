package com.powerroutine.service;

import com.powerroutine.Static.BodyStatic;
import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.controllerData.BodyData;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.interfaces.BodyCallBack;

public class BodyService {
    private BodyData bodyData=new BodyData();
    public void CargarBodys(){
        try{
            bodyData.getAllBodys(new BodyCallBack() {
                @Override
                public void onSuccess(BodyDTD bodyResponse) {
                    BodyStatic.bodyDTD=bodyResponse;
                }

                @Override
                public void onFailure(String errorMessage) {
                    if (BodyStatic.bodyDTD == null) {
                        BodyStatic.bodyDTD = new BodyDTD();
                    }
                    BodyStatic.bodyDTD.setRespuesta("error al cargar body");
                }
            });
        } catch (Exception e) {
            System.out.println("Error al cargar body: "+e.getMessage());
            BodyStatic.bodyDTD.setRespuesta("error al cargar body");
        }
    }
}
