package com.powerroutine.service;

import android.content.Context;
import android.widget.Toast;

import com.powerroutine.Static.BodyStatic;
import com.powerroutine.Static.UserCompletesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.UserCompletesData;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.UserCompletesDTD;
import com.powerroutine.interfaces.BodyCallBack;
import com.powerroutine.interfaces.UserCompletesCallBack;
import com.powerroutine.interfaces.UserCompletesSaveCallBack;
import com.powerroutine.model.UserCompletesModel;
import com.powerroutine.model.UserModel;

public class UserCompletesService {
    private UserCompletesData userCompletesData= new UserCompletesData();
    private UserModel user;

    public UserCompletesService() {
        user= UserStatic.user;
    }

    public void CargarItemComplete() {
        try {
            userCompletesData.getUserCompletes(user, new UserCompletesCallBack() {
                @Override
                public void onSuccess(UserCompletesDTD userCompletesDTD) {
                    UserCompletesStatic.userCompletesDTD=userCompletesDTD;
                }

                @Override
                public void onFailure(String errorMessage) {
                    if (UserCompletesStatic.userCompletesDTD == null) {
                        UserCompletesStatic.userCompletesDTD = new UserCompletesDTD();
                    }
                    UserCompletesStatic.userCompletesDTD.setRespuesta("fallo al cargar items");

                }
            });
        } catch (Exception e) {
            System.out.println("Error al cargar body: " + e.getMessage());
            UserCompletesStatic.userCompletesDTD.setRespuesta("error al cargar body");
        }


    }

    //PARA GUARDAD EJERCICIOS ES LA RUTINA MAS EL EJERCICIO y restar la cantidad de rutinas con usedayweek
    public void saveUserCompletes(UserCompletesModel userCompletesModel, Context context){
        try{
            userCompletesData.saveUserCompletes(userCompletesModel, new UserCompletesSaveCallBack() {
                @Override
                public void onSuccess(boolean response) {
                    UserCompletesStatic.userCompletesDTD.setRespuesta("item guardado");
                    UserCompletesStatic.userCompletesDTD.addItem(userCompletesModel);
                    Toast.makeText(context,"item guardado", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(boolean response) {
                    UserCompletesStatic.userCompletesDTD.setRespuesta("item no guardado");
                    Toast.makeText(context,"item no guardado", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            System.out.println("Error al cargar body: " + e.getMessage());
            UserCompletesStatic.userCompletesDTD.setRespuesta("item no guardado");
            UserCompletesStatic.userCompletesDTD.setRespuesta("error al cargar body");
            Toast.makeText(context,"item no guardado", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isCompleted(int id){
        if(UserCompletesStatic.userCompletesDTD.getUserCompletesModel() == null){
            return false;
        }
        for(UserCompletesModel userCompletes: UserCompletesStatic.userCompletesDTD.getUserCompletesModel()){
            if(userCompletes.getIdItem() == id){
                return true;
            }
        }
        return false;
    }
}
