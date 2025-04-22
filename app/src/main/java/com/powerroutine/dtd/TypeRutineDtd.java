package com.powerroutine.dtd;

import java.util.ArrayList;

public class TypeRutineDtd {
    private ArrayList<String> typeRutine;

    public TypeRutineDtd() {
        this.typeRutine = new ArrayList<>();
        typeRutine.add("Full body");
        typeRutine.add("Push Pull");
    }

    public TypeRutineDtd(int days) {
        if(days == 3){
            this.typeRutine = new ArrayList<>();
            typeRutine.add("Full body");
            typeRutine.add("Push Pull");
            typeRutine.add("Push Pull Leg");
        }else{
            this.typeRutine = new ArrayList<>();
        }
    }

    public ArrayList<String> getTypeRutine() {
        return typeRutine;
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
