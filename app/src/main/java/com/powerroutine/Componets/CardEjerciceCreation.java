package com.powerroutine.Componets;

import android.widget.TableLayout;

import com.powerroutine.Static.UserStatic;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import android.content.Context;


public class CardEjerciceCreation {
    private CardEjercice cardEjercice;
    private ArrayList<CardEjercice> cardsEjercices;
    private TableLayout tableLayout;

    public CardEjerciceCreation(TableLayout tableLayout,ArrayList<CardEjercice> cardsEjercices){
        this.cardsEjercices=cardsEjercices;
        this.tableLayout=tableLayout;
    }

    public CardEjerciceCreation(){this.cardsEjercices= new ArrayList<>();}

    public ArrayList<CardEjercice> createCardEjercices(Context context, ArrayList<EjerciceModel> ejercices){
        UserModel user= UserStatic.user;
        for(EjerciceModel ejercice:ejercices){
            int imgResId = context.getResources().getIdentifier(ejercice.getImage().toLowerCase(), "drawable", context.getPackageName());

            cardEjercice=new CardEjercice(
                    ejercice.getName(),
                    ejercice.getIdMuscle(),
                    ejercice.getDescripcion(),
                    imgResId,
                    ejercice.getId(),
                    user.getSeries(),
                    user.getRepeticiones()
            );

        }
    }

}
