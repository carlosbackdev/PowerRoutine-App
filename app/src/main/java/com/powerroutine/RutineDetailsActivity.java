package com.powerroutine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.powerroutine.Componets.CardEjercice;
import com.powerroutine.Componets.CardEjerciceCreation;
import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.BodyStatic;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.Static.ObjetiveStatic;
import com.powerroutine.Static.RutineStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.Thread.LoadStatic;
import com.powerroutine.controllerData.BodyData;
import com.powerroutine.dtd.BodyDTD;
import com.powerroutine.dtd.EjerciceDTD;
import com.powerroutine.dtd.MuscleDTD;
import com.powerroutine.dtd.ObjetiveDTD;
import com.powerroutine.interfaces.BodyCallBack;
import com.powerroutine.model.BodyModel;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RutineDetailsActivity extends AppCompatActivity {
    private RutineModel rutineModel;
    private UserModel user;
    private BodyData bodyData=new BodyData();

    private List<BodyModel> bodysModels;
    private TableLayout tableLayout;
    private LayoutInflater inflater;
    private ArrayList<CardEjercice> cardsComponents;
    private ArrayList<EjerciceModel> ejercices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_details);

        tableLayout=findViewById(R.id.tablaEjercicios);
        inflater=LayoutInflater.from(this);
        cardsComponents=new ArrayList<>();
        ejercices=new ArrayList<>();



        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        user= UserStatic.user;
        rutineModel = RutineStatic.rutina;
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"other");

        TextView titulo=findViewById(R.id.txtTitleDetailsRutine);
        titulo.setText(rutineModel.getType());
        System.out.println("rutina"+rutineModel.toString());

        LoadStatic loadStatic=new LoadStatic();
        System.out.println(loadStatic.toString());

        for(Integer id: rutineModel.getIdEjercices()){
            for(EjerciceModel ejercice: EjercicesStatic.ejerciceDTD.getEjercices()){
                if(ejercice.getId() == id){
                    ejercices.add(ejercice);
                }
            }
        }
        System.out.println(UserStatic.user.toString());

        cargarCardCompenent();

    }

    public void cargarCardCompenent(){
        //crear tarjetas
        CardEjerciceCreation cardEjerciceCreation=new CardEjerciceCreation(tableLayout,cardsComponents);
        cardsComponents = cardEjerciceCreation.createCardEjercices(this,ejercices);
        System.out.println("tamaño: "+cardsComponents.size());


        ArrayList<View> cardsView=new ArrayList<>();
        cardsView= crearCard(cardsView);
        tableLayout = cardEjerciceCreation.createLayoutCards(this,cardsView);
    }

    private ArrayList<View> crearCard(ArrayList<View> cardsView){
        for (CardEjercice cardEjercice : cardsComponents) {
            View card = inflater.inflate(R.layout.ejercice_card, null);

            TextView titulo = card.findViewById(R.id.txtTituloCard);
            ImageView imagen = card.findViewById(R.id.imgEjercice);
            TextView descipcion = card.findViewById(R.id.txtDescripcion);
            TextView musculo = card.findViewById(R.id.txtMusclePrincipal);
            TextView series = card.findViewById(R.id.txtSeries);
            TextView repeticiones = card.findViewById(R.id.txtRepeticiones);
            Button btnDetalles = card.findViewById(R.id.btnDetails);
            Button btnHecho = card.findViewById(R.id.btnComplete);

            titulo.setText(cardEjercice.getTitulo());
            descipcion.setText(cardEjercice.getDescripcion());
            musculo.setText(cardEjercice.getMuscle());
            imagen.setImageResource(cardEjercice.getImagenResId());
            series.setText(cardEjercice.getSeries());
            repeticiones.setText(cardEjercice.getRepeticiones());

            // Configurar márgenes
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            int margin = (int) getResources().getDimension(R.dimen.card_margin);
            params.setMargins(margin, margin, margin, margin);
            card.setLayoutParams(params);

            cardsView.add(card);
        }
        return cardsView;

    }


    public void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


}