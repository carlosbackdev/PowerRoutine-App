package com.powerroutine.Componets;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.powerroutine.model.UserModel;

public class UserSession {
    private static final String PREFS_NAME = "user_prefs";
    private static final String USER_KEY = "current_user";

    public static void saveUserSession(Context context, UserModel user) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Convertir el objeto UserModel a JSON
        Gson gson = new Gson();
        String userJson = gson.toJson(user);

        editor.putString(USER_KEY, userJson);
        editor.apply();
    }

    public static UserModel getUserSession(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userJson = preferences.getString(USER_KEY, null);

        if (userJson != null) {
            // Convertir el JSON a un objeto UserModel
            Gson gson = new Gson();
            return gson.fromJson(userJson, UserModel.class);
        }

        return null; // Si no existe, devolver null
    }

    public static void clearUserSession(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(USER_KEY);
        editor.apply();
    }

}
