package com.powerroutine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
    private TextView txtDay;
    private int day;
    private String dayString;
    private UserModel user;
    private TableLayout tableLayout;
    private LayoutInflater inflater;
    private ArrayList<RutineModel> rutinas;
    private RutinaData rutineData;
    private int userDayWeek;
    private CardRutine cardRutine;
    private ArrayList<CardRutine> cardsCompent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_seleceted);

        this.txtDay = findViewById(R.id.txtDay);
        this.user = (UserModel) getIntent().getSerializableExtra("user");
        userDayWeek=Integer.parseInt(user.getDaysWeek().toString());
        day=1;
        dayString = txtDay.getText().toString();
        txtDay.setText(dayString+" "+day);

        tableLayout=findViewById(R.id.tablaRutinas);
        inflater=LayoutInflater.from(this);
        rutinas=new ArrayList<>();
        rutineData=new RutinaData();
        cardsCompent=new ArrayList<>();

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
            cardRutine=new CardRutine(rutina.getName(),rutina.getType(),imgResId);
            cardsCompent.add(cardRutine);
        }
        cargarTarjetas(cardsCompent);
    }

    private void cargarTarjetas(ArrayList<CardRutine> cardsCompent) {
        tableLayout.removeAllViews();

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
    }

    private View crearCard(CardRutine cardsCompent) {
        View card = inflater.inflate(R.layout.rutina_card, null);

        TextView titulo = card.findViewById(R.id.txtTituloCard);
        TextView descripcion = card.findViewById(R.id.txtDescCard);
        ImageView imagen = card.findViewById(R.id.imgRutina);

        titulo.setText(cardsCompent.getTitulo());
        descripcion.setText(cardsCompent.getDescripcion());
        imagen.setImageResource(cardsCompent.getImagenResId());

        // Puedes añadir un onClickListener aquí si quieres que abra otra vista
        card.setOnClickListener(v -> {
            Toast.makeText(this, "Rutina: " + cardsCompent.getTitulo(), Toast.LENGTH_SHORT).show();
            // Aquí podrías cargar otro layout dinámicamente o abrir un detalle
        });

        return card;
    }
    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }
}