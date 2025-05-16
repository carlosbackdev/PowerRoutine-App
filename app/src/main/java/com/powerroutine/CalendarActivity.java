package com.powerroutine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.RutinesListStatic;
import com.powerroutine.Static.UserCompletesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserCompletesModel;
import com.powerroutine.model.UserModel;

public class CalendarActivity extends AppCompatActivity {
    private boolean weekComlpete;
    private UserModel user;
    private TextView txtCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"calendar");
        txtCalendar=findViewById(R.id.txtDayCalendar);

        System.out.println("USUARIO CALENDAR: "+ UserStatic.user);
        user= UserStatic.user;

        int contador=0;
        weekComlpete=false;

        for(RutineModel rutina: RutinesListStatic.rutinas.getRutinas()){
            for(UserCompletesModel userCompletesModel: UserCompletesStatic.userCompletesDTD.getUserCompletesModel()){
                if(userCompletesModel.getIdItem() == rutina.getId()){
                    if(userCompletesModel.isCompleted()){
                        contador++;
                    }
                }
            }
        }
        if(contador == user.getDaysWeek()){
            weekComlpete=true;
        }

        if(weekComlpete){
            txtCalendar.setText("¡Semana Completada!");
        }else{
            txtCalendar.setText("Rutinas por completar esta semana:"+(user.getDaysWeek()-contador));
        }

    }

    public void url(View v){
        Uri uri = Uri.parse("https://www.dietas.net/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);


    }
}