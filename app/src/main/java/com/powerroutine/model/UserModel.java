package com.powerroutine.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer daysWeek;
    private int level;
    private String objective;

    public int getLevel() {
        return level;
    }

    public String getObjective() {
        return objective;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setObjective(String objective) {
        this.objective = objective;
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
                ", level=" + level +
                ", objective='" + objective + '\'' +
                '}';
    }
}
