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
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.EjerciceData;
import com.powerroutine.controllerData.RutinaData;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.interfaces.UpdateRutineUserCallBack;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EjercicesSelectedActivity extends AppCompatActivity {
    private UserModel user;
    private RutinaData rutineData;
    private EjerciceData ejerciceData;
    private ArrayList<RutineModel> rutinas;
    private Spinner spnTypeRutine;
    private TextView titleRutine,txtChoiceRest;
    private String titleRutineString,restChoice;
    private ArrayList<EjerciceModel> ejercices,ejercicesOfRutine,ejeciciosSelected;
    private ArrayList<CardRutine> cardsCompent= new ArrayList<>();
    private TableLayout tableLayout;
    private LayoutInflater inflater;
    private boolean isFirstSelection = true;
    private int ejercicesMax,positionRutine;
    private Button btnSave;

    private ArrayList<String> typeRutine=new ArrayList<>();
    private Intent homeActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercices_selected);

        this.user = UserStatic.user;
        this.btnSave=findViewById(R.id.btnSave);
        this.spnTypeRutine=findViewById(R.id.spnTypeRutine);
        this.titleRutine=findViewById(R.id.txtEjerciceTitle);
        titleRutineString=titleRutine.getText().toString();
        this.txtChoiceRest=findViewById(R.id.txtDayRestChoice);
        restChoice=txtChoiceRest.getText().toString();
        this.tableLayout=findViewById(R.id.tablaEjercicios);
        this.inflater=LayoutInflater.from(this);


        rutineData=new RutinaData();
        ejerciceData= new EjerciceData();
        rutinas= new ArrayList<>();
        ejercicesOfRutine=new ArrayList<>();
        ejercices= new ArrayList<>();
        ejeciciosSelected= new ArrayList<>();

        homeActivity = new Intent(this, HomeActivity.class);


        ejerciciesMaxChoice();
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
                    int contador=1;
                    Collections.shuffle(rutinas);
                    for(RutineModel rutina: rutinas){
                        typeRutine.add("dia " +contador+" "+rutina.getType());
                        contador++;
                    }
                    titleRutine.setText(titleRutineString+": "+rutinas.get(0).getName());

                    //guardar los ejercicios de la rutina en la vista
                    saveEjercicesFromApi(rutineListDtd);

                    //metodo para cargar la rutina selecionada
                    spinerAdapter();

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

    private void saveEjercicesFromApi(RutineListDtd rutineListDtd){
        ejercices= EjercicesStatic.ejerciceDTD.getEjercices();
        loadEjercices(rutinas.get(0).getIdEjercices(),rutinas.get(0));
    }

    private void loadEjercices(List<Integer> ejercicesId, RutineModel rutine) {

        if (ejercices == null || ejercicesId == null || rutine == null) {
            System.out.println("Error: Datos no inicializados (ejercices, ejercicesId o rutine son null).");
            System.out.println("ejercices: "+ejercices);
            return;
        }
        ejercicesOfRutine.clear();
        for(int i=0; i<ejercicesId.size();i++){
            for(EjerciceModel ejercice: ejercices){
                if(ejercicesId.get(i) == ejercice.getId()){
                    ejercicesOfRutine.add(ejercice);
                }
            }
        }
        cargarCardCompenent();
    }

    public void cargarCardCompenent(){
        //crear tarjetas
        CardCreation cardCreation = new CardCreation(tableLayout,cardsCompent);
        cardsCompent = cardCreation.createCardsEjercices(this, ejercicesOfRutine);

        //filtrar los ejercicios que ya fueron seleccionados
        if (ejeciciosSelected.size() > 0) {
            for (int i = 0; i < ejeciciosSelected.size(); i++) {
                int ejercicioId = ejeciciosSelected.get(i).getId();
                for(int j = 0; j < cardsCompent.size(); j++) {
                    if (ejercicioId == cardsCompent.get(j).getId()) {
                        cardsCompent.remove(j);
                        break;
                    }
                }
            }
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
                for(EjerciceModel ejercice: ejercices){
                    if(ejercice.getId() == cardComponent.getId()){
                        ejeciciosSelected.add(ejercice);
                        cardsCompent.clear();
                        System.out.println("ejercicio guardado"+ejercice.toString());
                        restEjercice();
                        break;
                    }
                }
                cargarCardCompenent();
            });
            cardView.add(card);
        }
        return cardView;
    }

    private void ejerciciesMaxChoice() {
        if(user.getLevel()==1){
            ejercicesMax=4;
        } else if (user.getLevel() == 2) {
            ejercicesMax=5;
        }else{
            ejercicesMax=6;
        }
        txtChoiceRest.setText(restChoice+" "+ejercicesMax);
    }

    private void restEjercice(){
        if(ejercicesMax>0){
            ejercicesMax--;
        }
        txtChoiceRest.setText(restChoice+" "+ejercicesMax);
        if(ejercicesMax==0){
            cardsCompent.clear();
            ejercicesOfRutine.clear();
            cargarCardCompenent();
            mostrarToast("Has alcanzado el maximo de ejercicios");
            System.out.println("ejercicios seleccionados totales"+ejeciciosSelected.toString());
            btnSave.setBackgroundResource(R.drawable.button_background_orange);
        }
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiar(){
        ejerciciesMaxChoice();
        ejeciciosSelected.clear();
        ejercicesOfRutine.clear();
        cardsCompent.clear();
        ejerciciesMaxChoice();
        btnSave.setBackgroundResource(R.drawable.button_background_dark);

    }

    public void reset(View v){
        ejerciciesMaxChoice();
        ejeciciosSelected.clear();
        ejercicesOfRutine.clear();
        cardsCompent.clear();
        ejerciciesMaxChoice();
        btnSave.setBackgroundResource(R.drawable.button_background_dark);
        typeRutine.clear();
        CargarRutinas();

    }

    public void saveSelected(View v){
        //guardar la rutina seleccionada con la posicion de la rutina y los ejercicios seleccionados
        //para hacer un update en los ejercicios de la rutina del usuario
        RutineModel rutineUpdate=rutinas.get(positionRutine);
        List<Integer> ejercicesUpdate=new ArrayList<>();
        for(EjerciceModel ejercice: ejeciciosSelected){
            ejercicesUpdate.add(ejercice.getId());
        }
        rutineUpdate.setIdEjercices(ejercicesUpdate);
        System.out.println("rutina a actualizar"+rutineUpdate.toString());
        //guardar la rutina actualizada

        try{
            rutineData.updateRutineUser(rutineUpdate,new UpdateRutineUserCallBack() {
                @Override
                public void onSuccess(String response) {
                    System.out.println("respuesta"+response);
                    mostrarToast(response);
                    typeRutine.remove(positionRutine);
                    rutinas.remove(positionRutine);
                    spinerAdapter();
                    System.out.println("ejercicios selcecionads"+ejeciciosSelected);
                    if(rutinas.isEmpty()){
                        UserStatic.user=user;
                        startActivity(homeActivity);
                        finish();
                    }
                }
                @Override
                public void onFailure(String error) {
                    System.out.println("Error al cargar ejercicios: "+error);
                    mostrarToast("Error al cargar ejercicios:");
                }
            });

        }catch (Exception e){
            System.out.println("Error al cargar rutinas: "+e.getMessage());
            mostrarToast("Error al cargar rutinas:");
        }

    }

    public void spinerAdapter(){
        //metodo para cargar la rutina selecionada
        ArrayAdapter<String> adapter = new ArrayAdapter<>(EjercicesSelectedActivity.this, android.R.layout.simple_selectable_list_item, typeRutine);
        spnTypeRutine.setAdapter(adapter);
        spnTypeRutine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstSelection) {
                    isFirstSelection = false; // Cambiar el estado después de la primera ejecución
                    return; // Salir sin ejecutar la lógica
                }
                //logica para cargar los ejercicios de la rutina seleccionada
                //los ejercicios se cargan todos luego se filtar por el id aqui
                positionRutine=position;
                limpiar();
                loadEjercices(rutinas.get(position).getIdEjercices(), rutinas.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}

