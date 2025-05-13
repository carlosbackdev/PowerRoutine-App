package com.powerroutine.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.UserData;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.interfaces.DeleteUserCallBack;
import com.powerroutine.interfaces.UpdateUserCallBack;
import com.powerroutine.model.UserModel;

public class UserService {
    private UserData userData=new UserData();
    private LoginDtd loginDtd= new LoginDtd();

    public void updateUser(UserModel user, Context context) {
        try {
            userData.updateUser(user, new UpdateUserCallBack() {
                @Override
                public void onSuccess(LoginDtd loginResponse) {
                    loginDtd = loginResponse;
                    System.out.println("User updated successfully");
                    UserStatic.user=loginDtd.getUserModel();
                    Toast.makeText(context,loginDtd.getRespuesta(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(String errorMessage) {
                    if (errorMessage == null || errorMessage.isEmpty()) {
                        errorMessage = "Unknown error occurred.";
                    }
                    Toast.makeText(context,errorMessage, Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(UserModel user, Context context, Intent intent){
        try{
            userData.deleteUser(user, new DeleteUserCallBack() {
                @Override
                public void onSuccess(boolean response) {
                    if (response){
                        UserStatic.user=null;
                        Toast.makeText(context,"Usuario eliminado", Toast.LENGTH_SHORT).show();
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context,"No se puede eliminar el Usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(context,"Problema al eliminar usuario"+errorMessage, Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
