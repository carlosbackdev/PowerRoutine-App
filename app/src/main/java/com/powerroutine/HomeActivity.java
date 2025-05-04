package com.powerroutine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
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

import com.powerroutine.Componets.CardCreation;
import com.powerroutine.Componets.CardHome;
import com.powerroutine.Componets.CardHomeCreation;
import com.powerroutine.Componets.CardRutine;
import com.powerroutine.Componets.Navegator;
import com.powerroutine.controllerData.RutinaData;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {
    private RutinaData rutineData;
    private UserModel user;
    private ArrayList<RutineModel> rutinas;
    private TableLayout tableLayout;
    private ArrayList<CardHome> cardsCompent;
    private LayoutInflater inflater;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageView btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);

        tableLayout=findViewById(R.id.tablaEjercicios);
        inflater=LayoutInflater.from(this);


        new Navegator(btnHome,btnPerfil,btnCalendar,this,"home");
        rutineData = new RutinaData();
        cardsCompent=new ArrayList<>();

        user = (UserModel) getIntent().getSerializableExtra("user");

        CargarRutinas();

    }

    public void CargarRutinas(){
        try{
            rutineData.getRutinesForUser(user,new RutineListCallBack() {
                @Override
                public void onSuccess(RutineListDtd rutineListDtd) {
                    rutinas=rutineListDtd.getRutinas();
                    cargarCardCompenent();
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
    public void cargarCardCompenent(){
        //crear tarjetas
        CardHomeCreation cardCreation = new CardHomeCreation(tableLayout,cardsCompent);
        cardsCompent = cardCreation.createCardsRutines(this,rutinas);


        ArrayList<View> cardsView=new ArrayList<>();
        cardsView= crearCard(cardsView);
        tableLayout = cardCreation.createLayoutCards(this, cardsView);
    }

    private ArrayList<View> crearCard(ArrayList<View> cardView ) {
        for (CardHome cardComponent : cardsCompent) {
            View card = inflater.inflate(R.layout.rutina_card, null);

            TextView titulo = card.findViewById(R.id.txtTituloCard);
            TextView descripcion = card.findViewById(R.id.Layout);
            ImageView imagen = card.findViewById(R.id.imgRutina);

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
                        mostrarToast("rutina: "+rutina.getType());
                        break;
                    }
                }
            });
            cardView.add(card);
        }
        return cardView;
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }
}