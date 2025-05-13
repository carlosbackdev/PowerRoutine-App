package com.powerroutine.Componets;


import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

    public class Theme {

        private static final String PREFS_NAME = "theme_prefs";
        private static final String THEME_KEY = "current_theme";

        public static final int THEME_LIGHT = AppCompatDelegate.MODE_NIGHT_NO;
        public static final int THEME_DARK = AppCompatDelegate.MODE_NIGHT_YES;

        public static void loadTheme(Context context) {
            SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            int theme = preferences.getInt(THEME_KEY, THEME_DARK);
            AppCompatDelegate.setDefaultNightMode(theme);
        }


        public static void setTheme(Context context, String theme) {
            int newTheme;

            if ("dark".equalsIgnoreCase(theme)) {
                newTheme = THEME_DARK;
            } else if ("light".equalsIgnoreCase(theme)) {
                newTheme = THEME_LIGHT;
            } else {
                throw new IllegalArgumentException("Theme must be 'dark' or 'light'");
            }

            SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(THEME_KEY, newTheme);
            editor.apply();

            AppCompatDelegate.setDefaultNightMode(newTheme);
        }

}
