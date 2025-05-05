package com.powerroutine;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.Componets.Navegator;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

public class RutineDetailsActivity extends AppCompatActivity {
    private RutineModel rutineModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_details);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        UserModel user = (UserModel) getIntent().getSerializableExtra("user");
        rutineModel = (RutineModel) getIntent().getSerializableExtra("rutine");
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"other",user);

        TextView titulo=findViewById(R.id.txtTitleDetailsRutine);
        titulo.setText(rutineModel.getType());
        System.out.println(rutineModel.toString());



    }
}