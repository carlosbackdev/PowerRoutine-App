package com.powerroutine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.Static.UserCompletesStatic;
import com.powerroutine.Static.UserPreferencesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.Thread.Cronometro;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.MuscleModel;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserCompletesModel;
import com.powerroutine.model.UserModel;
import com.powerroutine.model.UserPreferences;
import com.powerroutine.service.UserCompletesService;

public class EjerciceDetailsActivity extends AppCompatActivity {

    private EjerciceModel ejercie;
    private MuscleModel muscle;
    private UserPreferences userPreferences;
    private ImageView imgEjerciceTecnica,imgEjerciceMuscle;
    private TextView txtRepeticiones,txtSeries,txtDescanso,txtMaterial,txtDescripcion,txtSeriesCounter,txtTitleMuscle,txtTitleDetailsEjercice;
    private Chronometer cronometro;
    private Button btnStart,btnStop,btnReset,btnComplete;
    private double maxTime;
    private int contadorSeries,seriesMax,seriesMin;
    private UserModel user;
    private boolean isComplete;
    private RutineModel rutineModel= new RutineModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercice_details);

        txtTitleDetailsEjercice=findViewById(R.id.txtTitleDetailsEjercice);
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
        user= UserStatic.user;

        rutineModel=(RutineModel) getIntent().getSerializableExtra("rutine");

        cargarDatos();
    }
    private void cargarVista(){
        int imgResIdEjercice = this.getResources().getIdentifier(ejercie.getImage().toLowerCase(), "drawable", this.getPackageName());
        int imgResIdMuscle = this.getResources().getIdentifier(muscle.getImage().toLowerCase(), "drawable", this.getPackageName());

        txtTitleDetailsEjercice.setText(ejercie.getName());
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

        itemCompleted();

    }

    private void cargarDatos(){
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

        System.out.println("debe haber uno"+UserCompletesStatic.userCompletesDTD);

        UserCompletesService userCompletesService=new UserCompletesService();
        int n=0;
        for (Integer ids: rutineModel.getIdEjercices()){
            n+=ids;
        }
        n+=user.getId();
        if(userCompletesService.isCompleted(rutineModel.getId()+ n + ejercie.getId())){
            isComplete=true;
        }
        cargarVista();
    }
    private void itemCompleted(){
        if(isComplete){
            btnComplete.setBackgroundResource(R.drawable.button_background_green);
        }else {
            btnComplete.setBackgroundResource(R.drawable.button_background_dark);
        }
    }

    public void start(View v){
        cronometro.start();
        btnStart.setBackgroundResource(R.drawable.button_background_dark);
        btnStop.setBackgroundResource(R.drawable.button_background_orange);
        btnReset.setBackgroundResource(R.drawable.button_background_orange);

        Cronometro cronometroClass = new Cronometro(cronometro,btnStart,btnStop,maxTime);
        cronometroClass.start();


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
        Intent rutineDetailsActivity=new Intent(this,RutineDetailsActivity.class);
        startActivity(rutineDetailsActivity);
        finish();
    }
    public void complete(View v){
        if(!isComplete){
            UserCompletesModel userCompletesModel=new UserCompletesModel();
            int n=0;
            for (Integer ids: rutineModel.getIdEjercices()){
                n+=ids;
            }
            n+=user.getId();
            userCompletesModel.setIdItem(rutineModel.getId()+ n + ejercie.getId());
            userCompletesModel.setIdUser(user.getId().intValue());
            userCompletesModel.setCompleted(true);
            UserCompletesService userCompletesService=new UserCompletesService();
            userCompletesService.saveUserCompletes(userCompletesModel, this);
            isComplete=true;
            itemCompleted();
        }

    }




    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

}