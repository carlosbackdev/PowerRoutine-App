package com.powerroutine.model;

public class EjerciceModel {
    private Integer id;
    private String name;
    private String descripcion;
    private  Integer idBody;
    private  Integer idMuscle;
    private String image;
    private String material;

    private boolean basic;

    public EjerciceModel() {
    }

    @Override
    public String toString() {
        return "EjerciceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idBody=" + idBody +
                ", idMuscle=" + idMuscle +
                ", image='" + image + '\'' +
                ", material='" + material + '\'' +
                ", basic=" + basic +
                '}';
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdBody(Integer idBody) {
        this.idBody = idBody;
    }

    public void setIdMuscle(Integer idMuscle) {
        this.idMuscle = idMuscle;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getIdBody() {
        return idBody;
    }

    public Integer getIdMuscle() {
        return idMuscle;
    }

    public String getImage() {
        return image;
    }

    public String getMaterial() {
        return material;
    }
}
