package com.powerroutine.Thread;

import com.powerroutine.Static.BodyStatic;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.LevelStatic;
import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.Static.ObjetiveStatic;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.LevelDTD;
import com.powerroutine.dtd.MuscleDTD;
import com.powerroutine.dtd.ObjetiveDTD;
import com.powerroutine.service.BodyService;
import com.powerroutine.service.EjerciceService;
import com.powerroutine.service.LevelService;
import com.powerroutine.service.MuscleService;
import com.powerroutine.service.ObjetiveService;
import com.powerroutine.service.UserCompletesService;

public class LoadStatic extends Thread{
    private MuscleDTD muscleDTD= MuscleStatic.muscleDTD;
    private BodyDTD bodyDTD= BodyStatic.bodyDTD;
    private EjerciceDTD ejerciceDTD= EjercicesStatic.ejerciceDTD;
    private ObjetiveDTD objetiveDTD= ObjetiveStatic.objetiveDTD;
    private LevelDTD levelDTD= LevelStatic.levelDTD;

    public void run(){
        MuscleService muscleService = new MuscleService();
        muscleService.CargarMusculos();

        BodyService bodyService = new BodyService();
        bodyService.CargarBodys();

        EjerciceService ejerciceService=new EjerciceService();
        ejerciceService.CargarEjercicios();

        ObjetiveService objetiveService =new ObjetiveService();
        objetiveService.CargarEjercicios();

        LevelService levelService=new LevelService();
        levelService.cargarLevels();
    }

    @Override
    public String toString() {
        return "LoadStatic{\n" +
                "muscleDTD=" + muscleDTD + ",\n" +
                "bodyDTD=" + bodyDTD + ",\n" +
                "ejerciceDTD=" + ejerciceDTD + ",\n" +
                "objetiveDTD=" + objetiveDTD + "\n" +
                "LevelDTD=" + levelDTD + "\n" +
                '}';
    }
}
