package com.powerroutine.service;

import com.powerroutine.Static.BodyStatic;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.controllerData.EjerciceData;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.interfaces.EjerciceCallBack;

public class EjerciceService {
    private EjerciceData ejerciceData = new EjerciceData();

    public void CargarEjercicios(){
        try{
            ejerciceData.getAllEjercices(new EjerciceCallBack() {
                @Override
                public void onSuccess(EjerciceDTD ejerciceResponse) {
                    EjercicesStatic.ejerciceDTD=ejerciceResponse;
                }
                @Override
                public void onFailure(String errorMessage) {
                    if (EjercicesStatic.ejerciceDTD == null) {
                        EjercicesStatic.ejerciceDTD = new EjerciceDTD();
                    }
                    EjercicesStatic.ejerciceDTD.setRespuesta("error al cargar ejercicios");
                    System.out.println("error al cargar ejercicios");

                }

            });

        } catch (Exception e) {
            System.out.println("Error al cargar ejercicios: "+e.getMessage());
        }
    }

}
