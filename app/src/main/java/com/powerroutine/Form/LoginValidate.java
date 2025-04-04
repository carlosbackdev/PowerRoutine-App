package com.powerroutine.Form;

import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

public class LoginValidate {
    private EditText username;
    private EditText password;

    public LoginValidate() {
    }

    public LoginValidate(EditText username, EditText password) {
        this.username = username;
        this.password = password;
    }

    public boolean validate(){
    String name=username.getText().toString().trim();
    String pass=password.getText().toString().trim();
    username.setTextColor(Color.WHITE);
    password.setTextColor(Color.WHITE);
    boolean validado=true;

    if(name.length()<4){
        username.setText("Nombre o Email invalido");
        username.setTextColor(Color.RED);
        validado = false;
    }
    if(password.length()<6){
        password.setText("Contraseña invalida");
        password.setTextColor(Color.RED);
        validado = false;
    }

    return validado;

    }
}
