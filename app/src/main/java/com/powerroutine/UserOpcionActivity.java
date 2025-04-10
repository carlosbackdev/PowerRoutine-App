package com.powerroutine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.model.UserModel;

public class UserOpcionActivity extends AppCompatActivity {
    private UserModel user;
    private Button btnDays, btnObjetive, btnLevel;
    private int opcionDays, opcionLevel;
    private String opcionObjetive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_opcion);

        user = (UserModel) getIntent().getSerializableExtra("user");

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
        System.out.println(opcionObjetive);
    }

    public void savePreferences(View v) {
        if (opcionDays != 0 && opcionLevel != 0 && opcionObjetive != null) {
            user.setDaysWeek(opcionDays);
            user.setLevel(opcionLevel);
            user.setObjective(opcionObjetive);
            System.out.println(user.toString());
        }
    }
}