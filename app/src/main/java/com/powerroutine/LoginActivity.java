package com.powerroutine;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.inputmethod.InputMethodManager;


import com.powerroutine.ControllerData.UserData;
import com.powerroutine.Form.LoginValidate;
import com.powerroutine.Model.UserModel;

public class LoginActivity extends AppCompatActivity {
    private EditText txtNombre,txtPasswd;
    private UserModel user;
    private LoginValidate form;
    private UserData userData;

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

    public void login(View V){
        foco();
        if(form.validate()){
            insertarModelo();

            userData.login(user);

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
            user.setEmail(txtNombre.getText().toString().trim());
            txtNombre.setText("email");
        }else{
            user.setUsername(txtNombre.getText().toString().trim());
        }
        user.setPassword(txtPasswd.getText().toString().trim());

    }

}