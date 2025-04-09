package com.powerroutine.form;

import android.graphics.Color;
import android.widget.EditText;

public class LoginValidate {
    private EditText username;
    private EditText password;
    private EditText email;


    public LoginValidate(EditText username, EditText password) {
        this.username = username;
        this.password = password;
    }
    public LoginValidate(EditText username, EditText password,EditText email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
    public boolean validateRegister(){
        String mail=email.getText().toString().trim();
        email.setTextColor(Color.WHITE);
        boolean validado = true;

        if(!mail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            email.setText("Email inválido");
            email.setTextColor(Color.RED);
            validado = false;
        }

        if(!validate()){
            validado = false;
        }
        return  validado;
    }
}
