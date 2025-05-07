package com.powerroutine.service;

import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.controllerData.MuscleData;
import com.powerroutine.dtd.MuscleDTD;
import com.powerroutine.interfaces.MuscleCallBack;

public class MuscleService {
    private MuscleData muscleData=new MuscleData();

    public void CargarMusculos(){
        try{
            muscleData.getAllMuscles(new MuscleCallBack() {
                @Override
                public void onSuccess(MuscleDTD muscleResponse) {
                    MuscleStatic.muscleDTD=muscleResponse;
                }

                @Override
                public void onFailure(String errorMessage) {
                    MuscleStatic.muscleDTD.setRespuesta("error al cargar los musculos");
                }
            });

        }catch(Exception e){
            System.out.println("Error al cargar rutinas: "+e.getMessage());
            MuscleStatic.muscleDTD.setRespuesta("error al cargar los musculos");
        }
    }
}
