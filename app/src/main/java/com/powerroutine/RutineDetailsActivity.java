package com.powerroutine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.powerroutine.Componets.CardEjercice;
import com.powerroutine.Componets.CardEjerciceCreation;
import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.RutineStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.Thread.LoadStatic;
import com.powerroutine.controllerData.BodyData;
import com.powerroutine.model.BodyModel;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserCompletesModel;
import com.powerroutine.model.UserModel;
import com.powerroutine.service.UserCompletesService;

import java.util.ArrayList;
import java.util.List;

public class RutineDetailsActivity extends AppCompatActivity {
    private RutineModel rutineModel;
    private UserModel user;

    private TableLayout tableLayout;
    private LayoutInflater inflater;
    private ArrayList<CardEjercice> cardsComponents;
    private ArrayList<EjerciceModel> ejercices;
    private Intent ejerciceDetailsActivity;
    private List<Integer> CompletesEjercice=new ArrayList<>();
    private Button btnComplete;
    private  UserCompletesService userCompletesService=new UserCompletesService();
    private boolean isCompleteRutine=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_details);

        tableLayout=findViewById(R.id.tablaEjercicios);
        inflater=LayoutInflater.from(this);
        cardsComponents=new ArrayList<>();
        ejercices=new ArrayList<>();
        ejerciceDetailsActivity=new Intent(this,EjerciceDetailsActivity.class);


        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        btnComplete=findViewById(R.id.btnCompleteRutine);
        user= UserStatic.user;
        rutineModel = RutineStatic.rutina;
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"other");

        TextView titulo=findViewById(R.id.txtTitleDetailsEjercice);
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

        if(userCompletesService.isCompleted(rutineModel.getId())){
            btnComplete.setBackgroundResource(R.drawable.button_background_green);
            isCompleteRutine=true;
        }


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
            Button btnHecho = card.findViewById(R.id.btnComplete);

            titulo.setText(cardEjercice.getTitulo());
            String desc=cardEjercice.getDescripcion();
            if(desc.length()<31){
                desc+="\n";
            }
            descipcion.setText(desc);
            musculo.setText(cardEjercice.getMuscle());
            imagen.setImageResource(cardEjercice.getImagenResId());
            series.setText(cardEjercice.getSeries());
            repeticiones.setText(cardEjercice.getRepeticiones());

            int n=0;
            for (Integer ids: rutineModel.getIdEjercices()){
                n+=ids;
            }
            n+=user.getId();
            if(userCompletesService.isCompleted(rutineModel.getId()+ n + cardEjercice.getId() )){
                CompletesEjercice.add(cardEjercice.getId());
                btnHecho.setBackgroundResource(R.drawable.button_background_green);
            }


            card.setOnClickListener(v -> {
                ejerciceDetailsActivity.putExtra("id",cardEjercice.getId());
                ejerciceDetailsActivity.putExtra("rutine",rutineModel);
                startActivity(ejerciceDetailsActivity);
            });

            // Configurar onClick para btnHecho
            btnHecho.setOnClickListener(v -> {
                if (!CompletesEjercice.contains(rutineModel.getId() + cardEjercice.getId())) {
                    // Crear el modelo y guardar el estado como completado
                    UserCompletesModel userCompletesModel = new UserCompletesModel();
                    int n2=0;
                    for (Integer ids: rutineModel.getIdEjercices()){
                        n2+=ids;
                    }
                    n2+=user.getId();
                    userCompletesModel.setIdItem(rutineModel.getId()+ n2 + cardEjercice.getId());
                    userCompletesModel.setIdUser(user.getId().intValue());
                    userCompletesModel.setCompleted(true);
                    userCompletesService.saveUserCompletes(userCompletesModel, this);
                    CompletesEjercice.add(rutineModel.getId()+ cardEjercice.getId());

                    btnHecho.setBackgroundResource(R.drawable.button_background_green);
                }
            });

            // Configurar márgenes
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            int margin = 20;
            params.setMargins(margin, margin, margin, margin);
            card.setLayoutParams(params);

            cardsView.add(card);
        }
        return cardsView;

    }

    public void complete(View v){
        if(!isCompleteRutine){
            UserCompletesModel userCompletesModel=new UserCompletesModel();
            userCompletesModel.setIdItem(rutineModel.getId());
            userCompletesModel.setIdUser(user.getId().intValue());
            userCompletesModel.setCompleted(true);
            UserCompletesService userCompletesService=new UserCompletesService();
            userCompletesService.saveUserCompletes(userCompletesModel, this);
            isCompleteRutine=true;
            btnComplete.setBackgroundResource(R.drawable.button_background_green);
        }
    }

    public void back(View v){
        Intent HomeActivity=new Intent(this,HomeActivity.class);
        startActivity(HomeActivity);
        finish();
    }

}