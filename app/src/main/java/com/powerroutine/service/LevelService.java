package com.powerroutine.service;

import com.powerroutine.Static.LevelStatic;
import com.powerroutine.controllerData.LevelData;
import com.powerroutine.dtd.LevelDTD;
import com.powerroutine.interfaces.LevelCallBack;

public class LevelService {
    private LevelData levelData=new LevelData();

    public void cargarLevels(){
        try{
            levelData.getAllLevels(new LevelCallBack() {
                @Override
                public void onSuccess(LevelDTD levelDTD) {
                    LevelStatic.levelDTD=levelDTD;
                }

                @Override
                public void onFailure(String errorMessage) {
                    LevelStatic.levelDTD.setRespuesta("error al cargar los musculos");
                }
            });

        }catch(Exception e){
            System.out.println("Error al cargar rutinas: "+e.getMessage());
            LevelStatic.levelDTD.setRespuesta("error al cargar los musculos");
        }
    }
}
