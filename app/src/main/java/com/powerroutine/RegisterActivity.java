package com.powerroutine;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.powerroutine.Static.UserStatic;
import com.powerroutine.interfaces.RegisterCallBack;
import com.powerroutine.controllerData.UserData;
import com.powerroutine.form.LoginValidate;
import com.powerroutine.model.UserModel;
import com.powerroutine.utils.FocoChange;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtName,txtEmail,txtPassword;
    private LoginValidate validate;
    private UserModel user;
    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        txtName=findViewById(R.id.txtNombre);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        validate=new LoginValidate(txtName,txtPassword,txtEmail);
        user=new UserModel();
        userData=new UserData();

        txtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                txtName.setText("");
                txtName.setTextColor(Color.WHITE);
            }
        });

        txtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                txtEmail.setText("");
                txtEmail.setTextColor(Color.WHITE);
            }
        });


        txtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                txtPassword.setText("");
                txtPassword.setTextColor(Color.WHITE);
            }
        });

    }
    public void register(View v){
        FocoChange.focoChange(this);
        if (validate.validateRegister()){
            InsertarModelo();
            try {
                userData.register(user, new RegisterCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        if(response.contains("correctamente")){
                            login(null);
                            UserStatic.user=user;
                            mostrarToast(response);
                        }else {
                            mostrarToast(response);
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        System.out.println("error en el onFailure"+errorMessage);
                        if (errorMessage == null || errorMessage.isEmpty()) {
                            errorMessage = "Unknown error occurred.";
                        }else{
                            if (errorMessage.contains("usuario")){
                                txtName.setText(errorMessage);
                                txtName.setTextColor(Color.RED);
                            }else if(errorMessage.contains("email")){
                                txtEmail.setText(errorMessage);
                                txtEmail.setTextColor(Color.RED);
                            }
                        }
                    }
                });
            }catch (Exception e){
                System.out.println(e);
                throw new RuntimeException(e);
            }

        }

    }

    public void InsertarModelo(){
        user.setEmail(txtEmail.getText().toString().toLowerCase());
        user.setName(txtName.getText().toString().toLowerCase());
        user.setPassword(txtPassword.getText().toString());
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    public void login(View v){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}