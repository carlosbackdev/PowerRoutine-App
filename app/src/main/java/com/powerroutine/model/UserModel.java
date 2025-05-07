package com.powerroutine.model;

import java.io.Serializable;

public class UserModel{
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer daysWeek;
    private Integer idLevelRange;
    private Integer idObjetive;

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

    public int getRepeticiones(){
        if(idObjetive==1){
            return 15;
        } else if (idObjetive==2) {
            return 12;
        }
        return 10;
    }
    public int getSeries(){
        if(idLevelRange==1){
            return 3;
        } else if (idLevelRange==2) {
            return 4;
        }
        return 5;
    }
}
