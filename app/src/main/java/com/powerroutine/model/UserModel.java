package com.powerroutine.model;

import com.powerroutine.Static.LevelStatic;
import com.powerroutine.Static.ObjetiveStatic;

import java.io.Serializable;

public class UserModel{
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer daysWeek;
    private Integer idLevelRange;
    private Integer idObjetive;

    public String getLevelString(){
        if(LevelStatic.levelDTD.getLevels() !=null){
            for (LevelRange level : LevelStatic.levelDTD.getLevels()) {
                if(level.getId() == idLevelRange){
                    return level.getName();
                }
            }
        }
        return "id"+idLevelRange;
    }
    public String getObjetiveString(){
        if(ObjetiveStatic.objetiveDTD.getObjetives() !=null){
            for (ObjetiveModel objetive : ObjetiveStatic.objetiveDTD.getObjetives()) {
                if(objetive.getId() == idObjetive){
                    return objetive.getName();
                }
            }
        }
        return "id"+idObjetive;
    }

    public void setObjetive(Integer idObjective) {
        this.idObjetive = idObjective;
    }

    public Integer getObjetive() {
        return idObjetive;
    }

    public int getLevel() {
        return idLevelRange;
    }

    public void setLevel(int level) {
        this.idLevelRange = level;
    }

    public Integer getDaysWeek() {
        return daysWeek;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDaysWeek(Integer daysWeek) {
        this.daysWeek = daysWeek;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", daysWeek=" + daysWeek +
                ", level=" + idLevelRange +
                ", objective=" + idObjetive +
                '}';
    }

}
