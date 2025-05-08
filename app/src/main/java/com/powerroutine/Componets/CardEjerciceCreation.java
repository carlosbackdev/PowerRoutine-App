package com.powerroutine.Componets;

import android.view.View;
import android.widget.TableLayout;

import com.powerroutine.R;
import com.powerroutine.Static.UserPreferencesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.UserModel;
import com.powerroutine.model.UserPreferences;

import java.util.ArrayList;
import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;


public class CardEjerciceCreation {
    private CardEjercice cardEjercice;
    private ArrayList<CardEjercice> cardsEjercices;
    private TableLayout tableLayout;
    private UserPreferences userPreferences= UserPreferencesStatic.userPreferences;

    public CardEjerciceCreation(TableLayout tableLayout,ArrayList<CardEjercice> cardsEjercices){
        this.cardsEjercices=cardsEjercices;
        this.tableLayout=tableLayout;
    }

    public CardEjerciceCreation(){this.cardsEjercices= new ArrayList<>();}

    public ArrayList<CardEjercice> createCardEjercices(Context context, ArrayList<EjerciceModel> ejercices){
        UserModel user= UserStatic.user;
        for(EjerciceModel ejercice:ejercices){
            int imgResId = context.getResources().getIdentifier(ejercice.getImage().toLowerCase(), "drawable", context.getPackageName());

            String repeticiones="";
            if(ejercice.isBasic()){
               int rep=Integer.parseInt(userPreferences.getObjetive().getRangeRep());
               rep-=4;
               if(rep<5){
                   rep=5;
               }
                repeticiones=String.valueOf(rep);
            }else {
                repeticiones=userPreferences.getObjetive().getRangeRep();
            }

            cardEjercice=new CardEjercice(
                    ejercice.getName(),
                    ejercice.getIdMuscle(),
                    ejercice.getDescripcion(),
                    imgResId,
                    ejercice.getId(),
                    userPreferences.getLevelRange().getRangeSeries(),
                    repeticiones
            );
            cardsEjercices.add(cardEjercice);
        }
        return cardsEjercices;
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
