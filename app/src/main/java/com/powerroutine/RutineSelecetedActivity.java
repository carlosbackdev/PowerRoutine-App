package com.powerroutine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.powerroutine.Componets.CardCreation;
import com.powerroutine.Componets.CardRutine;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.RutinaData;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.interfaces.RutineUserCallback;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RutineSelecetedActivity extends AppCompatActivity {
    private TextView txtDay,txtDayRestChoice;
    private Button btnSave;
    private int day,dayRestInt;
    private String dayString,dayRest;
    private UserModel user;
    private TableLayout tableLayout;
    private LayoutInflater inflater;
    private ArrayList<RutineModel> rutinas;
    private RutinaData rutineData;
    private ArrayList<CardRutine> cardsCompent;
    private ArrayList<RutineModel> rutinaSaved;
    private List<Integer> incompatibles;
    private RutineListDtd saveRutineList=new RutineListDtd();
    private TypeRutineDtd typeRutineDtd;
    private Spinner spnTypeRutine;
    private String typeRutine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_seleceted);

        this.txtDay = findViewById(R.id.txtEjerciceTitle);
        this.user = UserStatic.user;
        this.btnSave=findViewById(R.id.btnSave);
        this.txtDayRestChoice= findViewById(R.id.txtDayRestChoice);

        tableLayout=findViewById(R.id.tablaEjercicios);
        inflater=LayoutInflater.from(this);
        rutinas=new ArrayList<>();
        rutineData=new RutinaData();
        cardsCompent=new ArrayList<>();
        rutinaSaved= new ArrayList<>();
        incompatibles=new ArrayList<>();
        day=0;
        dayRestInt=user.getDaysWeek();
        dayString = txtDay.getText().toString();
        dayRest= txtDayRestChoice.getText().toString();
        this.typeRutineDtd= new TypeRutineDtd(user.getDaysWeek());
        spnTypeRutine=findViewById(R.id.spnTypeRutine);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, typeRutineDtd.getTypeRutine());
        spnTypeRutine.setAdapter(adapter);
        typeRutine=typeRutineDtd.getTypeRutine().get(0);

        // Configurar el listener para el Spinner
        spnTypeRutine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = parent.getItemAtPosition(position).toString();
                typeRutine = selectedType;
                reset(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dayMore();
        CargarRutinas();
    }

    public void CargarRutinas(){
        try{
            rutineData.getRutinesForDay(user,new RutineListCallBack() {
                @Override
                public void onSuccess(RutineListDtd rutineListDtd) {
                    rutinas=rutineListDtd.getRutinas();
                    cargarCardCompenent();
                }

                @Override
                public void onFailure(String error) {
                    System.out.println("Error al cargar rutinas:"+error);
                    mostrarToast("Error al cargar rutinas:");
                }
            });

        }catch (Exception e){
            System.out.println("Error al cargar rutinas: "+e.getMessage());
            mostrarToast("Error al cargar rutinas:");
        }
    }
    public void cargarCardCompenent(){
        //crear tarjetas
        CardCreation cardCreation = new CardCreation(tableLayout,cardsCompent);
        cardsCompent = cardCreation.createCardsRutines(this, rutinas, typeRutine);

        if (rutinaSaved.size()>0) {
            for (int i = 0; i < cardsCompent.size(); i++) {
                if (incompatibles != null && incompatibles.contains(cardsCompent.get(i).getId())) {
                    cardsCompent.remove(i);
                    i--;
                }
            }
        }
        if(txtDay.getText().toString().equals("Completo")){
            cardsCompent.clear();
        }

        ArrayList<View> cardsView=new ArrayList<>();
        cardsView= crearCard(cardsView);
        tableLayout = cardCreation.createLayoutCards(this, cardsView);
    }
    private ArrayList<View> crearCard(ArrayList<View> cardView ) {
        for (CardRutine cardComponent : cardsCompent) {
            View card = inflater.inflate(R.layout.rutina_card, null);

            TextView titulo = card.findViewById(R.id.txtTituloCard);
            TextView descripcion = card.findViewById(R.id.Layout);
            ImageView imagen = card.findViewById(R.id.imgEjercice);

            titulo.setText(cardComponent.getTitulo());
            descripcion.setText(cardComponent.getDescripcion());
            imagen.setImageResource(cardComponent.getImagenResId());

            // Configurar márgenes
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            int margin = (int) getResources().getDimension(R.dimen.card_margin);
            params.setMargins(margin, margin, margin, margin);
            card.setLayoutParams(params);

            card.setOnClickListener(v -> {
                for(RutineModel rutina: rutinas){
                    if(rutina.getId() == cardComponent.getId()){
                        rutinaSaved.add(rutina);
                        incompatibles.addAll(rutina.getRutineIncompatible());
                        incompatibles.add(rutina.getId());
                        System.out.println("rutina guardada"+rutinaSaved.toString());
                        break;
                    }
                }
                dayMore();
                cargarCardCompenent();
            });
            cardView.add(card);
        }
        return cardView;
    }
    public void dayMore(){
        txtDay.setText("");
        int userDayWeek=Integer.parseInt(user.getDaysWeek().toString());

        if(userDayWeek > day){
            day++;
            txtDay.setText(dayString+" "+day);
        }else {
            btnSave.setBackgroundResource(R.drawable.button_background_orange);
            txtDay.setText("Completo");
        }

        if(dayRestInt > 0){
            txtDayRestChoice.setText(dayRest+" "+dayRestInt);
            dayRestInt--;
        }else{
            txtDayRestChoice.setText("Guarda y pasa a seleccionar los ejercicios");
        }

    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    public void reset(View v){
        day=0;
        dayRestInt=user.getDaysWeek();
        incompatibles.clear();
        rutinaSaved.clear();
        cardsCompent.clear();
        btnSave.setBackgroundResource(R.drawable.button_background_dark);
        dayMore();
        CargarRutinas();
    }
    public void saveSelected(View v){
        if(rutinaSaved.size() == user.getDaysWeek()){
            saveRutineList.setUser(user);
            saveRutineList.setRutinas(rutinaSaved);
            System.out.println(saveRutineList.toString());
            rutineData.saveRutineUser(saveRutineList, new RutineUserCallback() {
                @Override
                public void onSuccess(String response) {
                    System.out.println("respuesta:"+response);
                    mostrarToast(response);

                }

                @Override
                public void onFailure(String error) {
                    System.out.println("Error al guardar rutinas: "+error);
                }
            });


            Intent intent = new Intent(this, EjercicesSelectedActivity.class);
            UserStatic.user=user;
            startActivity(intent);
        }
    }
}