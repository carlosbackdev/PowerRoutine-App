package com.powerroutine;

import android.content.Intent;
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

import com.powerroutine.Componets.CardHome;
import com.powerroutine.Componets.CardHomeCreation;
import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.RutineStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.controllerData.RutinaData;
import com.powerroutine.dtd.RutineListDtd;
import com.powerroutine.interfaces.RutineListCallBack;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RutinaData rutineData;
    private UserModel user= UserStatic.user;
    private ArrayList<RutineModel> rutinas;
    private TableLayout tableLayout;
    private ArrayList<CardHome> cardsCompent;
    private LayoutInflater inflater;

    private Intent rutineDetailsActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);

        tableLayout=findViewById(R.id.tablaEjercicios);
        inflater=LayoutInflater.from(this);


        user = UserStatic.user;

        new Navegator(btnHome,btnPerfil,btnCalendar,this,"home");
        rutineData = new RutinaData();
        cardsCompent=new ArrayList<>();

        rutineDetailsActivity=new Intent(this,RutineDetailsActivity.class);


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
            View card = inflater.inflate(R.layout.rutina_home, null);

            TextView titulo = card.findViewById(R.id.txtTituloCard);
            TextView descripcion = card.findViewById(R.id.txtDescCard);
            ImageView imagen = card.findViewById(R.id.imgEjercice);
            TextView completed=card.findViewById(R.id.txtCompleteCard);

            titulo.setText(cardComponent.getTitulo());
            descripcion.setText(cardComponent.getDescripcion());
            imagen.setImageResource(cardComponent.getImagenResId());
            completed.setText(cardComponent.getCompletadoText());
            if(!cardComponent.getCompletado()){
                completed.setTextColor(getResources().getColor(R.color.secundary_color));
            }else{
                completed.setTextColor(getResources().getColor(R.color.text_grey));
            }

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
                        RutineStatic.rutina=rutina;
                        startActivity(rutineDetailsActivity);
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