package com.powerroutine;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.RutineStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.BodyData;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.interfaces.BodyCallBack;
import com.powerroutine.model.BodyModel;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.List;

public class RutineDetailsActivity extends AppCompatActivity {
    private RutineModel rutineModel;
    private UserModel user;
    private BodyData bodyData=new BodyData();

    private List<BodyModel> bodysModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_details);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        user= UserStatic.user;
        rutineModel = RutineStatic.rutina;
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"other");

        TextView titulo=findViewById(R.id.txtTitleDetailsRutine);
        titulo.setText(rutineModel.getType());
        System.out.println(rutineModel.toString());

        CargarBodys();

    }

    public void CargarBodys(){
        try{
            bodyData.getBodys(rutineModel.getIdBody(),new BodyCallBack() {
                @Override
                public void onSuccess(BodyDTD bodyResponse) {
                    bodysModels= bodyResponse.getBodys();
                    System.out.println(bodysModels.toString());
                }
                @Override
                public void onFailure(String error) {
                    System.out.println("Error al cargar body: "+error);
                    mostrarToast("Error al cargar body:");
                }
            });
        } catch (Exception e) {
            System.out.println("Error al cargar rutinas: "+e.getMessage());
            mostrarToast("Error al cargar rutinas:");
        }
    }

    public void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


}