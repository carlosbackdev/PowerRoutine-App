package com.powerroutine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;


import com.powerroutine.controllerData.UserData;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.form.LoginValidate;
import com.powerroutine.model.UserModel;
import com.powerroutine.interfaces.LoginCallback;
import com.powerroutine.utils.FocoChange;


public class LoginActivity extends AppCompatActivity {
    private EditText txtNombre,txtPasswd;
    private UserModel user;
    private LoginValidate form;
    private UserData userData;
    private LoginDtd loginDtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        this.txtNombre=findViewById(R.id.txtNombre);
        this.txtPasswd=findViewById(R.id.txtPassword);
        this.form= new LoginValidate(txtNombre,txtPasswd);
        this.userData=new UserData();
        this.user= new UserModel();


        txtNombre.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                txtNombre.setText("");
                txtNombre.setTextColor(Color.WHITE);
            }
        });

        txtPasswd.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                txtPasswd.setText("");
                txtPasswd.setTextColor(Color.WHITE);
            }
        });

    }

    public void login(View V) {
        FocoChange.focoChange(this);
        if (form.validate()) {
            insertarModelo();
            try{
                userData.login(user, new LoginCallback() {
                    @Override
                    public void onSuccess(LoginDtd loginResponse) {
                        // Aquí manejamos la respuesta exitosa
                        //aqui deber poner que si el oibjeto usuario tiene dias de semana o rutina deber poonerlo como saltar configuracion
                        loginDtd = loginResponse;
                        mostrarToast(loginDtd.getRespuesta());
                        System.out.println(loginDtd.toString());
                        if(loginDtd.getUserModel() != null){
                            user=loginDtd.getUserModel();
                            System.out.println(user.toString());
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Verificamos si errorMessage es nulo
                        if (errorMessage == null || errorMessage.isEmpty()) {
                            errorMessage = "Unknown error occurred.";
                        }
                        mostrarToast( errorMessage);
                        if(errorMessage.contains("usuario")){
                            txtNombre.setText(errorMessage);
                            txtNombre.setTextColor(Color.RED);
                        }else if(errorMessage.contains("Contraseña")){
                            txtPasswd.setText(errorMessage);
                            txtPasswd.setTextColor(Color.RED);
                        }
                    }
                });

            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
    }
    public void register(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void insertarModelo(){
        if(txtNombre.getText().toString().trim().contains("@")){
            user.setEmail(txtNombre.getText().toString().trim().toLowerCase());
        }else{
            user.setName(txtNombre.getText().toString().trim());
        }
        user.setPassword(txtPasswd.getText().toString().trim());

    }
    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

}