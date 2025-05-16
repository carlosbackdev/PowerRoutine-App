package com.powerroutine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.powerroutine.Componets.Theme;
import com.powerroutine.Componets.UserSession;
import com.powerroutine.Static.MuscleStatic;
import com.powerroutine.Static.UserPreferencesStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.Thread.LoadStatic;
import com.powerroutine.model.UserPreferences;
import com.powerroutine.service.UserCompletesService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Theme.loadTheme(this);


        setContentView(R.layout.activity_main);

        try {
            carga();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private void carga() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        executorService.execute(() -> {
            LoadStatic loadStaticHilo = new LoadStatic();
            loadStaticHilo.start();
            try {
                loadStaticHilo.join();

                int duration = 5500;
                int interval = 100;
                int steps = duration / interval;

                for (int i = 0; i <= steps; i++) {
                    int progress = (i * 100) / steps;
                    mainHandler.post(() -> progressBar.setProgress(progress));
                    Thread.sleep(interval);
                }

                nextActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void nextActivity(){
        System.out.println("terminado"+MuscleStatic.muscleDTD);
        Intent intent2= new Intent(this, LoginActivity.class);
        try {

            if(UserSession.getUserSession(this) != null){
                UserStatic.user= UserSession.getUserSession(this);
                try{
                    Thread.sleep(100);
                    UserCompletesService userCompletesService= new UserCompletesService();
                    userCompletesService.CargarItemComplete();
                    Thread.sleep(100);
                    UserPreferences userPreferences= new UserPreferences(UserStatic.user);
                    UserPreferencesStatic.userPreferences=userPreferences;
                    Thread.sleep(100);
                }catch (Exception e){
                    System.out.println("Error al cargar el usuario");
                }
                Intent intent= new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            if(UserStatic.user == null){
                startActivity(intent2);
            }

        }catch (Exception e){

        }


    }
}