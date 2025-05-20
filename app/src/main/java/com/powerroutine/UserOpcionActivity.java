package com.powerroutine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.UserData;
import com.powerroutine.dtd.LoginDtd;
import com.powerroutine.model.UserModel;
import com.powerroutine.service.UserService;

public class UserOpcionActivity extends AppCompatActivity {
    private UserModel user;
    private UserData userData;
    private LoginDtd loginDtd;
    private Button btnDays, btnObjetive, btnLevel;
    private Integer objetive;
    private int opcionDays, opcionLevel;
    private String opcionObjetive;
    private Intent rutineSelectedActivty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_opcion);

        user = UserStatic.user;
        userData= new UserData();
        loginDtd = new LoginDtd();
        rutineSelectedActivty = new Intent(this, RutineSelecetedActivity.class);


    }

    public void selectDays(View v) {
        if (btnDays != null && btnDays.getId() != v.getId()) {
            btnDays.setBackgroundResource(R.drawable.button_background_orange);

        }
        btnDays = (Button) v;

        btnDays.setBackgroundResource(R.drawable.button_background_dark);
        this.opcionDays = Integer.parseInt(btnDays.getText().toString());
        System.out.println(opcionDays);
    }

    public void selectLevel(View v) {
        if (btnLevel != null && btnLevel.getId() != v.getId()) {
            btnLevel.setBackgroundResource(R.drawable.button_background_orange);

        }
        btnLevel = (Button) v;

        btnLevel.setBackgroundResource(R.drawable.button_background_dark);
        String level = btnLevel.getText().toString();
        if (level.equalsIgnoreCase("principiante")) {
            this.opcionLevel = 1;
        }
        if (level.equalsIgnoreCase("intermedio")) {
            this.opcionLevel = 2;
        }
        if (level.equalsIgnoreCase("profesional")) {
            this.opcionLevel = 3;
        }
        System.out.println(opcionLevel);
    }

    public void selectObjetive(View v) {
        if (btnObjetive != null && btnObjetive.getId() != v.getId()) {
            btnObjetive.setBackgroundResource(R.drawable.button_background_orange);

        }
        btnObjetive = (Button) v;

        btnObjetive.setBackgroundResource(R.drawable.button_background_dark);
        this.opcionObjetive = btnObjetive.getText().toString();
        if (opcionObjetive.equalsIgnoreCase("hipertrofia")) {
            this.objetive =1;
        }
        if (opcionObjetive.equalsIgnoreCase("fuerza")) {
            this.objetive =2;
        }
        if (opcionObjetive.equalsIgnoreCase("tonificar")) {
            this.objetive =3;
        }
        System.out.println(opcionObjetive);
        System.out.println(objetive);
    }

    public void savePreferences(View v) {
        if (opcionDays != 0 && opcionLevel != 0 && opcionObjetive != null) {
            insertarModelo();
            UserService userService = new UserService();
            userService.updateUser(user, this);
            try{

                Thread.sleep(100);
                startActivity(rutineSelectedActivty);
                finish();

            }catch (Exception e){
                System.out.println(e);
            }

        }
    }
        public void insertarModelo() {
            user.setDaysWeek(opcionDays);
            user.setLevel(opcionLevel);
            user.setObjetive(objetive);
            UserStatic.user=user;
    }


}
