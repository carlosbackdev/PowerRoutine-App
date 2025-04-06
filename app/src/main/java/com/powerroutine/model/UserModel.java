package com.powerroutine.model;

public class UserModel {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer daysWeek;

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
                '}';
    }
}
