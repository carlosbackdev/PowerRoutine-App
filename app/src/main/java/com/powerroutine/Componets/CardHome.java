package com.powerroutine.Componets;

public class CardHome {
    private String titulo;
    private String descripcion;
    private int imagenResId;
    private boolean completado;
    private int id;

    public CardHome(String titulo, String descripcion, int imagenResId, boolean completado, int id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
        this.completado = completado;
        this.id = id;
    }

    @Override
    public String toString() {
        return "CardHome{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenResId=" + imagenResId +
                ", completado='" + completado + '\'' +
                ", id=" + id +
                '}';
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

    public void setCompletado(boolean completado) {
        this.completado = completado;
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

    public boolean getCompletado() {
        return completado;
    }

    public int getId() {
        return id;
    }
}
