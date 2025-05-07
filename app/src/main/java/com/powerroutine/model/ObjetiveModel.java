package com.powerroutine.model;

public class ObjetiveModel {
    private Integer id;

    private String name;
    private String rangeRep;

    @Override
    public String toString() {
        return "ObjetiveModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rangeRep='" + rangeRep + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRangeRep() {
        return rangeRep;
    }

    public void setRangeRep(String rangeRep) {
        this.rangeRep = rangeRep;
    }
}
