package com.powerroutine.Componets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.powerroutine.CalendarActivity;
import com.powerroutine.HomeActivity;
import com.powerroutine.PerfilActivity;
import com.powerroutine.R;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.model.UserModel;

import java.util.List;

public class Navegator {
    private ImageButton btnHome,btnCalendar,btnPerfil;
    private Context context;
    private String activity;

    private Intent home,perfil,calendar;


    public Navegator(ImageButton btnHome, ImageButton btnPerfil, ImageButton btnCalendar, Context context, String activity ) {
        this.btnHome = btnHome;
        this.btnPerfil = btnPerfil;
        this.btnCalendar = btnCalendar;
        this.context = context;
        this.activity=activity;

        home=new Intent(context, HomeActivity.class);
        perfil=new Intent(context, PerfilActivity.class);
        calendar=new Intent(context, CalendarActivity.class);


        setupListeners();
        backgroung();
    }
    private void setupListeners() {
        btnPerfil.setOnClickListener(this::perfil);
        btnCalendar.setOnClickListener(this::calendar);
        btnHome.setOnClickListener(this::home);
    }

    private void backgroung(){
        btnHome.setImageResource(R.drawable.rutineicon);
        btnCalendar.setImageResource(R.drawable.calendario);
        btnPerfil.setImageResource(R.drawable.perfil);

        if(activity.equals("home")) {
            btnHome.setBackground(ContextCompat.getDrawable(context, R.color.primary_color_intense));
        } else if (activity.equals("perfil")) {
            btnPerfil.setBackground(ContextCompat.getDrawable(context, R.color.primary_color_intense));
        } else if (activity.equals("calendar")) {
            btnCalendar.setBackground(ContextCompat.getDrawable(context, R.color.primary_color_intense));
        }
    }

    public void perfil(View v) {
        // Acción para el botón de perfil
        if(!activity.equals("perfil")){
            context.startActivity(perfil);
        }
    }

    public void calendar(View v) {
        // Acción para el botón de calendario
        if(!activity.equals("calendar")){
            context.startActivity(calendar);

        }
    }

    public void home(View v) {
        // Acción para el botón de inicio
        if(!activity.equals("home")){
            context.startActivity(home);

        }

    }
}
