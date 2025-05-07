package com.powerroutine.service;

import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.ObjetiveStatic;
import com.powerroutine.controllerData.ObjetiveData;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.ObjetiveDTD;
import com.powerroutine.interfaces.EjerciceCallBack;
import com.powerroutine.interfaces.ObjetiveCallBack;

public class ObjetiveService {
    private ObjetiveData objetiveData= new ObjetiveData();

    public void CargarEjercicios(){
        try{
            objetiveData.getAllObjetvies(new ObjetiveCallBack() {
                @Override
                public void onSuccess(ObjetiveDTD objetiveResponse) {
                    ObjetiveStatic.objetiveDTD=objetiveResponse;
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
