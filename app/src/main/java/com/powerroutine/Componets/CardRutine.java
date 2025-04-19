package com.powerroutine.Componets;

public class CardRutine {
    private String titulo;
    private String descripcion;
    private int imagenResId;
    private int id;

    public CardRutine(String titulo, String descripcion, int imagenResId, int id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }

    @Override
    public String toString() {
        return "RutinaDtd{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenResId=" + imagenResId +
                '}';
    }
}
