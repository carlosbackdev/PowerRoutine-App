package com.powerroutine.Componets;

import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.model.MuscleModel;

import java.util.List;

public class CardEjercice {
    private String titulo;
    private int idMuscle;
    private String descripcion;
    private int imagenResId;
    private int id;

    private int series;
    private int repeticiones;
    private String muscle;

    public CardEjercice(String titulo, int idMuscle, String descripcion, int imagenResId, int id, int series, int repeticiones) {
        this.titulo = titulo;
        this.idMuscle = idMuscle;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
        this.id = id;
        this.series = series;
        this.repeticiones = repeticiones;

        //coger los id de los musculos o poner su nombre desde cargados
        musculos();
    }

    private void musculos(){
        List<MuscleModel> musculos=MuscleStatic.muscleDTD.getMuscles();
        for(MuscleModel musculo:musculos){
            if(musculo.getId() == idMuscle){
                this.muscle=musculo.getName();
            }
        }

    }

    @Override
    public String toString() {
        return "CardEjercice{" +
                "titulo='" + titulo + '\'' +
                ", muscle='" + muscle + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenResId=" + imagenResId +
                ", id=" + id +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
}
