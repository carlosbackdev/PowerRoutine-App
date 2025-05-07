package com.powerroutine.model;

public class LevelRange {
    private Integer id;

    private String name;
    private Integer level;

    private String rangeSeries;

    @Override
    public String toString() {
        return "LevelRange{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", rangeSeries='" + rangeSeries + '\'' +
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRangeSeries() {
        return rangeSeries;
    }

    public void setRangeSeries(String rangeSeries) {
        this.rangeSeries = rangeSeries;
    }
}
