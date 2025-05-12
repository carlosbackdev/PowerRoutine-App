package com.powerroutine.model;

public class UserCompletesModel {

    private Integer id;
    private Integer idUser;

    //id mas musculo
    private Integer idItem;
    private boolean completed;

    public boolean itemIsCompleted(int idItem){
        if(this.idItem == idItem){
            return completed;
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserCompletesModel{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idItem=" + idItem +
                ", completed=" + completed +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
