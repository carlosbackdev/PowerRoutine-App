package com.powerroutine.Componets;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.powerroutine.R;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.RutineModel;

import java.util.ArrayList;

public class CardHomeCreation {
    private CardHome cardRutine;
    private ArrayList<CardHome> cardsCompent;
    private TableLayout tableLayout;

    public CardHomeCreation(TableLayout tableLayout,ArrayList<CardHome> cardsCompent){
        this.cardsCompent = cardsCompent;
        this.tableLayout=tableLayout;
    }
    public CardHomeCreation(){
        this.cardsCompent = new ArrayList<>();
    }



    public ArrayList<CardHome> createCardsRutines(Context context, ArrayList<RutineModel> rutinas) {

        for (RutineModel rutina: rutinas){
                int imgResId = context.getResources().getIdentifier(rutina.getImage().toLowerCase(), "drawable", context.getPackageName());
                //cambiar true por completadoget de la base de datos
                cardRutine=new CardHome(rutina.getName(),rutina.getType(),imgResId,rutina.isCompleted(),rutina.getId());
                cardsCompent.add(cardRutine);
        }

        return cardsCompent;
    }

    public TableLayout createLayoutCards(Context context,ArrayList<View> cardsView){
        tableLayout.removeAllViews();

        for (int i = 0; i < cardsView.size(); i++) {
            TableRow row = new TableRow(context);

            row.addView(cardsView.get(i));

            tableLayout.addView(row);
        }
        if(cardsView.size()==0){
            TextView emptyMessage = new TextView(context);
            emptyMessage.setTextAppearance(context, R.style.TextViewHeaderBlackStyle);
            emptyMessage.setTextColor(context.getResources().getColor(R.color.text_grey));
            emptyMessage.setText("No hay rutinas o ejercicios disponibles.");
            emptyMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            emptyMessage.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));
            tableLayout.addView(emptyMessage);
        }


        return tableLayout;
    }

}
