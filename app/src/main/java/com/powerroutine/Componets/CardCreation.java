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

public class CardCreation {
    private CardRutine cardRutine;
    private ArrayList<CardRutine> cardsCompent;
    private TableLayout tableLayout;

    public CardCreation(TableLayout tableLayout,ArrayList<CardRutine> cardsCompent){
        this.cardsCompent = cardsCompent;
        this.tableLayout=tableLayout;
    }
    public CardCreation(){
        this.cardsCompent = new ArrayList<>();
    }



    public ArrayList<CardRutine> createCardsRutines(Context context,ArrayList<RutineModel> rutinas, String typeRutine) {

        for (RutineModel rutina: rutinas){
            if(rutina.filtrarRutinaNombre(typeRutine)){
                int imgResId = context.getResources().getIdentifier(rutina.getImage().toLowerCase(), "drawable", context.getPackageName());
                cardRutine=new CardRutine(rutina.getName(),rutina.getType(),imgResId,rutina.getId());
                cardsCompent.add(cardRutine);

            }

        }

        return cardsCompent;
    }

    public ArrayList<CardRutine> createCardsEjercices(Context context, ArrayList<EjerciceModel> ejercices) {

        for (EjerciceModel ejercice: ejercices){
                int imgResId = context.getResources().getIdentifier(ejercice.getImage().toLowerCase(), "drawable", context.getPackageName());
                cardRutine=new CardRutine(ejercice.getName(),ejercice.getDescripcion(),imgResId,ejercice.getId());
                cardsCompent.add(cardRutine);
        }

        return cardsCompent;
    }

    public TableLayout createLayoutCards(Context context,ArrayList<View> cardsView){
        tableLayout.removeAllViews();

        for (int i = 0; i < cardsView.size(); i += 2) {
            TableRow row = new TableRow(context);

            row.addView(cardsView.get(i));

            // Carga la segunda tarjeta si existe
            if (i + 1 < cardsView.size()) {
                row.addView(cardsView.get(i+1));
            } else {
                // Si es impar, rellena con un espacio en blanco
                View emptyView = new View(context);
                emptyView.setLayoutParams(new TableRow.LayoutParams(0, 0, 1));
                row.addView(emptyView);
            }

            tableLayout.addView(row);
        }
        if(cardsView.size()==0){
            TextView emptyMessage = new TextView(context);
            emptyMessage.setTextAppearance(context, R.style.TextViewHeaderBlackStyle);
            emptyMessage.setTextColor(context.getResources().getColor(R.color.text_grey));
            emptyMessage.setText("No hay rutinas disponibles.");
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
