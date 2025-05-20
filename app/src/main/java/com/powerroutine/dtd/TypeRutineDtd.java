package com.powerroutine.dtd;

import com.powerroutine.Static.UserStatic;

import java.util.ArrayList;

public class TypeRutineDtd {
    private ArrayList<String> typeRutine;

    public TypeRutineDtd(int days) {
        this.typeRutine = new ArrayList<>();

        if(days == 2) {
            typeRutine.add("Full body");
        }
        if(days == 3){
            typeRutine.add("Full body");
            typeRutine.add("Push Pull");
            typeRutine.add("Push Pull Leg");
        }if(days == 4){
            typeRutine.add("Push Pull Basic");
            typeRutine.add("Weider Recomendada");
        }
    }

    public ArrayList<String> getTypeRutine() {
        return this.typeRutine;
    }

    public void setTypeRutine(ArrayList<String> typeRutine) {
        this.typeRutine = typeRutine;
    }

    @Override
    public String toString() {
        return "TypeRutineDtd{" +
                "typeRutine=" + typeRutine +
                '}';
    }
}
