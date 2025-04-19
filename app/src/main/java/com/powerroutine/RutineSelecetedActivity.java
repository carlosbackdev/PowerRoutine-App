package com.powerroutine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.powerroutine.Componets.CardRutine;
import com.powerroutine.controllerData.RutinaData;
import com.powerroutine.dtd.RutinaDtd;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
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
    private int userDayWeek;
    private CardRutine cardRutine;
    private ArrayList<CardRutine> cardsCompent;
    private ArrayList<RutineModel> rutinaSaved;
    private List<Integer> incompatibles;
    private RutineListDtd saveRutineList=new RutineListDtd();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_seleceted);

        this.txtDay = findViewById(R.id.txtDay);
        this.user = (UserModel) getIntent().getSerializableExtra("user");
        this.btnSave=findViewById(R.id.btnSave);
        this.txtDayRestChoice= findViewById(R.id.txtDayRestChoice);

        tableLayout=findViewById(R.id.tablaRutinas);
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

        dayMore();
        CargarRutinas();
    }

    public void CargarRutinas(){
        try{
            rutineData.getRutinesForDay(user,new RutineListCallBack() {
                @Override
                public void onSuccess(RutineListDtd rutineListDtd) {
                    rutinas=rutineListDtd.getRutinas();
                    mostrarToast(rutineListDtd.getRespuesta());

                    // cargarTarjetas(rutineListDtd.getRutinas());
                    cargarCardCompenent();
                }

                @Override
                public void onFailure(String error) {
                    System.out.println("Error al cargar rutinas: "+error);
                    Toast.makeText(RutineSelecetedActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            System.out.println("Error al cargar rutinas: "+e.getMessage());
        }
    }
    public void cargarCardCompenent(){
        for (RutineModel rutina: rutinas){
            int imgResId = getResources().getIdentifier(rutina.getImage(), "drawable", getPackageName());
            cardRutine=new CardRutine(rutina.getName(),rutina.getType(),imgResId,rutina.getId());
            cardsCompent.add(cardRutine);
        }
        cargarTarjetas();
    }

    private void cargarTarjetas() {
        tableLayout.removeAllViews();

        if (rutinaSaved.size()>0) {
            for (int i = 0; i < cardsCompent.size(); i++) {
                System.out.println("incompatibles"+incompatibles);
                System.out.println("cardsCompent.get(i).getId()"+cardsCompent.get(i).getId());

                if (incompatibles != null && incompatibles.contains(cardsCompent.get(i).getId())) {
                    cardsCompent.remove(i);
                    i--;
                }
            }
        }
        for (int i = 0; i < cardsCompent.size(); i += 2) {
            TableRow row = new TableRow(this);

            // Carga la primera tarjeta
            View card1 = crearCard(cardsCompent.get(i));
            row.addView(card1);

            // Carga la segunda tarjeta si existe
            if (i + 1 < cardsCompent.size()) {
                View card2 = crearCard(cardsCompent.get(i + 1));
                row.addView(card2);
            } else {
                // Si es impar, rellena con un espacio en blanco
                View emptyView = new View(this);
                emptyView.setLayoutParams(new TableRow.LayoutParams(0, 0, 1));
                row.addView(emptyView);
            }

            tableLayout.addView(row);
        }
        if(cardsCompent.size()==0){
            TextView emptyMessage = new TextView(this);
            emptyMessage.setTextAppearance(this, R.style.TextViewHeaderBlackStyle);
            emptyMessage.setTextColor(getResources().getColor(R.color.text_grey));
            emptyMessage.setText("No hay rutinas disponibles.");
            emptyMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            emptyMessage.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));
            tableLayout.addView(emptyMessage);
        }
    }

    private View crearCard(CardRutine cardsCompent) {
        View card = inflater.inflate(R.layout.rutina_card, null);

        TextView titulo = card.findViewById(R.id.txtTituloCard);
        TextView descripcion = card.findViewById(R.id.txtDescCard);
        ImageView imagen = card.findViewById(R.id.imgRutina);

        titulo.setText(cardsCompent.getTitulo());
        descripcion.setText(cardsCompent.getDescripcion());
        imagen.setImageResource(cardsCompent.getImagenResId());

        // Configurar márgenes
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        int margin = (int) getResources().getDimension(R.dimen.card_margin);
        params.setMargins(margin, margin, margin, margin);
        card.setLayoutParams(params);

        // Puedes añadir un onClickListener aquí si quieres que abra otra vista
        card.setOnClickListener(v -> {
            Toast.makeText(this, "Rutina: " + cardsCompent.getTitulo(), Toast.LENGTH_SHORT).show();
            // Aquí podrías cargar otro layout dinámicamente o abrir un detalle
            for(RutineModel rutina: rutinas){
                if(rutina.getId() == cardsCompent.getId()){
                    rutinaSaved.add(rutina);
                    incompatibles.addAll(rutina.getRutineIncompatible());
                    incompatibles.add(rutina.getId());
                    System.out.println("rutina guardada"+rutinaSaved.toString());
                    break;
                }
            }
            dayMore();
            cargarTarjetas();
        });

        return card;
    }
    public void dayMore(){
        txtDay.setText("");
        userDayWeek=Integer.parseInt(user.getDaysWeek().toString());
        if(userDayWeek > day){
            day++;
            txtDay.setText(dayString+" "+day);
        }else {
            mostrarToast("Guarda las rutinas selecionadas ");
            btnSave.setBackgroundResource(R.drawable.button_background_orange);
            txtDay.setText("Semana completa");
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
            mostrarToast("siii");
            saveRutineList.setUser(user);
            saveRutineList.setRutinas(rutinaSaved);


        }
    }
}