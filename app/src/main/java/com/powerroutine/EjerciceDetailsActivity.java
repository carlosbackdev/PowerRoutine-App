package com.powerroutine;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.Static.UserPreferencesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.MuscleModel;
import com.powerroutine.model.UserModel;
import com.powerroutine.model.UserPreferences;

public class EjerciceDetailsActivity extends AppCompatActivity {

    private EjerciceModel ejercie;
    private MuscleModel muscle;
    private UserPreferences userPreferences;
    private ImageView imgEjerciceTecnica,imgEjerciceMuscle;
    private TextView txtRepeticiones,txtSeries,txtDescanso,txtMaterial,txtDescripcion,txtSeriesCounter,txtTitleMuscle;
    private Chronometer cronometro;
    private Button btnStart,btnStop,btnReset,btnComplete;
    private double maxTime;
    private int contadorSeries,seriesMax,seriesMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercice_details);

        imgEjerciceTecnica=findViewById(R.id.imgEjerciceTecnica);
        imgEjerciceMuscle=findViewById(R.id.imgEjerciceMuscle);
        txtRepeticiones=findViewById(R.id.txtRepeticiones);
        txtSeries=findViewById(R.id.txtSeries);
        txtDescanso=findViewById(R.id.txtDescanso);
        txtMaterial=findViewById(R.id.txtMaterial);
        txtDescripcion=findViewById(R.id.txtDesc);
        txtSeriesCounter=findViewById(R.id.seriesCounter);
        txtTitleMuscle=findViewById(R.id.txtTItleMuscle);
        cronometro=findViewById(R.id.cronometro);
        btnStart=findViewById(R.id.btnStart);
        btnStop=findViewById(R.id.btnStop);
        btnReset=findViewById(R.id.btnReset);
        btnComplete=findViewById(R.id.btnComplete2);

        contadorSeries=0;


        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"other");

        userPreferences= UserPreferencesStatic.userPreferences;

        cargarDatos();
        cargarVista();
    }
    private void cargarVista(){
        int imgResIdEjercice = this.getResources().getIdentifier(ejercie.getImage().toLowerCase(), "drawable", this.getPackageName());
        int imgResIdMuscle = this.getResources().getIdentifier(muscle.getImage().toLowerCase(), "drawable", this.getPackageName());

        txtTitleMuscle.setText(muscle.getName());
        imgEjerciceMuscle.setImageResource(imgResIdMuscle);
        imgEjerciceTecnica.setImageResource(imgResIdEjercice);
        txtDescripcion.setText(ejercie.getDescripcion());
        txtMaterial.setText(ejercie.getMaterial());
        String repeticiones = String.valueOf(userPreferences.restarRepeticiones(ejercie));
        txtRepeticiones.setText(repeticiones);
        txtSeries.setText(userPreferences.getLevelRange().getRangeSeries());
        String descanso= String.valueOf(userPreferences.getDescanso(ejercie))+" m";
        txtDescanso.setText(descanso);

    }

    public void cargarDatos(){
        int id = getIntent().getIntExtra("id", -1);
        if(id != -1){
            for(EjerciceModel ejercicio: EjercicesStatic.ejerciceDTD.getEjercices()){
                if(ejercicio.getId() == id){
                    ejercie=ejercicio;
                }
            }
        }
        for(MuscleModel musc: MuscleStatic.muscleDTD.getMuscles()){
         if(musc.getId() == ejercie.getIdMuscle()){
             muscle=musc;
         }
        }
        maxTime=userPreferences.getDescanso(ejercie);

    }

    public void start(View v){
        cronometro.start();
        btnStart.setBackgroundResource(R.drawable.button_background_dark);
        btnStop.setBackgroundResource(R.drawable.button_background_orange);
        btnReset.setBackgroundResource(R.drawable.button_background_orange);
    }
    public void stop(View v){
        cronometro.stop();
        btnStart.setBackgroundResource(R.drawable.button_background_orange);
        btnStop.setBackgroundResource(R.drawable.button_background_dark);
    }
    public void reset(View v){
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.stop();
        btnStart.setBackgroundResource(R.drawable.button_background_orange);
        btnStop.setBackgroundResource(R.drawable.button_background_dark);
        btnReset.setBackgroundResource(R.drawable.button_background_dark);
    }

    public void more(View v){
        String s=userPreferences.getLevelRange().getRangeSeries();
        String c=s.charAt(0)+"";
        seriesMin=Integer.parseInt(c);
        seriesMax=seriesMin+1;
        System.out.println(seriesMin);
        System.out.println(seriesMax);


        contadorSeries++;

        if(contadorSeries >= seriesMin){
        btnComplete.setBackgroundResource(R.drawable.button_background_orange);
        }
        if(contadorSeries >= seriesMax){
            contadorSeries=seriesMax;
        }
        txtSeriesCounter.setText(String.valueOf(contadorSeries));
    }

    public void minus(View v){
        if (contadorSeries ==0){
            return;
        }
        if(contadorSeries > 0){
            contadorSeries--;
        }
        if(contadorSeries < seriesMin){
            btnComplete.setBackgroundResource(R.drawable.button_background_dark);
        }
        txtSeriesCounter.setText(String.valueOf(contadorSeries));

    }

    public void back(View v){
        finish();
    }

}