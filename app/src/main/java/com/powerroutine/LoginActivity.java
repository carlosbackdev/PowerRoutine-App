package com.powerroutine;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.powerroutine.controllerData.UserData;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.form.LoginValidate;
import com.powerroutine.model.UserModel;
import com.powerroutine.controllerData.LoginCallback;


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
        foco();
        if (form.validate()) {
            insertarModelo();
            System.out.println(user.toString());
            try{
                userData.login(user, new LoginCallback() {
                    @Override
                    public void onSuccess(LoginDtd loginResponse) {
                        // Aquí manejamos la respuesta exitosa
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


    public void foco(){
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
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