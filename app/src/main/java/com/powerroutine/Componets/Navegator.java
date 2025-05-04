package com.powerroutine.Componets;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Navegator {
    private ImageButton btnHome,btnCalendar;
    private ImageView btnPerfil;
    private Context context;
    private String activity;

    public Navegator(ImageButton btnHome, ImageView btnPerfil, ImageButton btnCalendar, Context context,String activity) {
        this.btnHome = btnHome;
        this.btnPerfil = btnPerfil;
        this.btnCalendar = btnCalendar;
        this.context = context;
        this.activity = activity;
        setupListeners();
    }
    private void setupListeners() {
        btnPerfil.setOnClickListener(this::perfil);
        btnCalendar.setOnClickListener(this::calendar);
        btnHome.setOnClickListener(this::home);
    }

    public void perfil(View v) {
        // Acción para el botón de perfil
        if(!activity.equals("perfil")){

        }
    }

    public void calendar(View v) {
        // Acción para el botón de calendario
        if(!activity.equals("calendar")){

        }
    }

    public void home(View v) {
        // Acción para el botón de inicio
        if(!activity.equals("home")){

        }else {
            btnHome.setBackgroundColor(Color.WHITE);
        }

    }
}
