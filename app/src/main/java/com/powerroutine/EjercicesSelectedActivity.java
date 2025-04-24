package com.powerroutine;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.controllerData.RutinaData;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class EjercicesSelectedActivity extends AppCompatActivity {
    private UserModel user;
    private RutinaData rutineData;
    private ArrayList<RutineModel> rutinas;
    private Spinner spnTypeRutine;
    private TextView titleRutine,txtChoiceRest;
    private String titleRutineString,restChoice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercices_selected);

        this.user = (UserModel) getIntent().getSerializableExtra("user");
        rutineData=new RutinaData();
        rutinas= new ArrayList<>();
        spnTypeRutine=findViewById(R.id.spnTypeRutine);
        titleRutine=findViewById(R.id.txtEjerciceTitle);
        titleRutineString=titleRutine.getText().toString();
        txtChoiceRest=findViewById(R.id.txtDayRestChoice);
        ejerciciesMaxChoice();


        //cargar los ejercicios del usuario para luego hacer el spinner por cada rutina guardada
        CargarRutinas();
        System.out.println(rutinas.toString());



    }
    public void CargarRutinas(){
        try{
            rutineData.getRutinesForUser(user,new RutineListCallBack() {
                @Override
                public void onSuccess(RutineListDtd rutineListDtd) {
                    rutinas=rutineListDtd.getRutinas();
                    System.out.println("rutinas encontradas"+rutinas.toString());
                    ArrayList<String> typeRutine=new ArrayList<>();
                    int contador=1;
                    for(RutineModel rutina: rutinas){
                        typeRutine.add("dia " +contador+" "+rutina.getType());
                        contador++;
                    }
                    titleRutine.setText(titleRutineString+": "+rutinas.get(0).getName());

                    System.out.println("rutinas tipo"+typeRutine.toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(EjercicesSelectedActivity.this, android.R.layout.simple_selectable_list_item, typeRutine);
                    spnTypeRutine.setAdapter(adapter);
                    spnTypeRutine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //logica para cargar los ejercicios de la rutina seleccionada
                            loadEjercices(rutinas.get(position).getIdEjercices());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void onFailure(String error) {
                    System.out.println("Error al cargar rutinas: "+error);
                    mostrarToast("Error al cargar rutinas:");
                }
            });

        }catch (Exception e){
            System.out.println("Error al cargar rutinas: "+e.getMessage());
            mostrarToast("Error al cargar rutinas:");
        }
    }

    private void loadEjercices(List<Integer> ejercices) {
        System.out.println("ejercices"+ejercices.toString());
    }

    private void ejerciciesMaxChoice() {
        restChoice=txtChoiceRest.getText().toString();
        if(user.getLevel()==1){
            txtChoiceRest.setText(restChoice+" "+"4");
        } else if (user.getLevel() == 2) {
            txtChoiceRest.setText(restChoice+" "+"5");
        }else{
            txtChoiceRest.setText(restChoice+" "+"6");
        }
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }
}

