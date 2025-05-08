package com.powerroutine.model;

import com.powerroutine.Static.LevelStatic;
import com.powerroutine.Static.ObjetiveStatic;
import com.powerroutine.Static.UserStatic;

public class UserPreferences {
    private UserModel user= UserStatic.user;
    private LevelRange levelRange;
    private ObjetiveModel objetive;

    public UserPreferences() {
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
