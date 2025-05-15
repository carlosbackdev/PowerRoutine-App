package com.powerroutine;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.Componets.Theme;
import com.powerroutine.Componets.UserSession;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.Thread.LoadStatic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Theme.loadTheme(this);

        setContentView(R.layout.activity_main);


        LoadStatic loadStaticHilo = new LoadStatic();
        try {
            loadStaticHilo.start();
            loadStaticHilo.join();
            Intent intent= new Intent(this, LoginActivity.class);
            if(UserSession.getUserSession(this) != null){
                UserStatic.user= UserSession.getUserSession(this);
                intent= new Intent(this, HomeActivity.class);
            }
            startActivity(intent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}