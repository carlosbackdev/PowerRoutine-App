package com.powerroutine;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.model.UserModel;

public class PerfilActivity extends AppCompatActivity {
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        user= UserStatic.user;
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"perfil");


    }
}