package com.powerroutine.model;


import java.util.List;

public class RutineModel {
    private int id;
    private long idUser;
    private int idRutine;
    private String name;
    private List<Integer> idBody;
    private List<Integer> idEjercices;
    private String image;
    private List<Integer> rutineIncompatible;
    private String type;
    private List<Integer> complement;
    private List<Integer> muscle_principal;
    private String dayweek;
    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getIdUser() {
        return idUser;
    }

    public int getIdRutine() {
        return idRutine;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public void setIdRutine(int idRutine) {
        this.idRutine = idRutine;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIdBody() {
        return idBody;
    }

    public void setIdBody(List<Integer> idBody) {
        this.idBody = idBody;
    }

    public List<Integer> getIdEjercices() {
        return idEjercices;
    }

    public void setIdEjercices(List<Integer> idEjercices) {
        this.idEjercices = idEjercices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getRutineIncompatible() {
        return rutineIncompatible;
    }

    public void setRutineIncompatible(List<Integer> rutineIncompatible) {
        this.rutineIncompatible = rutineIncompatible;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getComplement() {
        return complement;
    }

    public void setComplement(List<Integer> complement) {
        this.complement = complement;
    }

    public List<Integer> getMuscle_principal() {
        return muscle_principal;
    }

    public void setMuscle_principal(List<Integer> muscle_principal) {
        this.muscle_principal = muscle_principal;
    }

    public String getDayweek() {
        return dayweek;
    }

    public void setDayweek(String dayweek) {
        this.dayweek = dayweek;
    }

    public boolean filtrarRutinaNombre(String nombre) {
        if(nombre.equalsIgnoreCase(this.name)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "RutineModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idBody=" + idBody +
                ", idEjercices=" + idEjercices +
                ", image='" + image + '\'' +
                ", rutineIncompatible=" + rutineIncompatible +
                ", type='" + type + '\'' +
                ", complement=" + complement +
                ", muscle_principal=" + muscle_principal +
                ", dayweek='" + dayweek + '\'' +
                '}';
    }
}