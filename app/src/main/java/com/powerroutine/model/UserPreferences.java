package com.powerroutine.model;

import com.powerroutine.Static.LevelStatic;
import com.powerroutine.Static.ObjetiveStatic;
import com.powerroutine.Static.UserStatic;

public class UserPreferences {
    private UserModel user= UserStatic.user;
    private LevelRange levelRange;
    private ObjetiveModel objetive;

    public UserPreferences(UserModel user) {
        this.user= user;
        if(this.user == null){
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        onCreate();
    }

    public void onCreate() {
        levelRange = new LevelRange();
        for (LevelRange level : LevelStatic.levelDTD.getLevels()) {
            if (level.getId() == user.getLevel()) {
                levelRange = level;
            }

        }
        objetive = new ObjetiveModel();
        for(ObjetiveModel objetiveModel: ObjetiveStatic.objetiveDTD.getObjetives()){
            if(objetiveModel.getId() == user.getObjetive()){
                objetive = objetiveModel;
            }
        }

    }

    public int restarRepeticiones(EjerciceModel ejerciceModel) {
        if (ejerciceModel.isBasic()) {
            int rep = Integer.parseInt(objetive.getRangeRep());
            rep -= 4;
            if (rep < 5) {
                rep = 5;
            }
            return rep;
        }
        return Integer.parseInt(objetive.getRangeRep());
    }

    public double getDescanso(EjerciceModel ejerciceModel){
        double descanso=0.0;
        if(user.getObjetive() ==1){
            descanso=5;
        } else if (user.getObjetive() ==2) {
            descanso=3.5;
        }else {
            descanso=1.5;
        }
        if(!ejerciceModel.isBasic()){
            descanso-=1.5;
        }
        if (descanso==0){
            descanso=1;
        }
        return descanso;

    }


    public LevelRange getLevelRange() {
        return levelRange;
    }

    public void setLevelRange(LevelRange levelRange) {
        this.levelRange = levelRange;
    }

    public ObjetiveModel getObjetive() {
        return objetive;
    }

    public void setObjetive(ObjetiveModel objetive) {
        this.objetive = objetive;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "user=" + user +
                ", levelRange=" + levelRange +
                ", objetive=" + objetive +
                '}';
    }
}
