package com.powerroutine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

import com.powerroutine.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RutineSelecetedActivity extends AppCompatActivity {
    private TextView txtDay;
    private int day;
    private String dayString;
    private UserModel user;
    private TableLayout tableLayout;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutine_seleceted);

        this.txtDay = findViewById(R.id.txtDay);
        day=1;
        dayString = txtDay.getText().toString();
        txtDay.setText(dayString+" "+day);
        tableLayout=findViewById(R.id.tablaRutinas);
        inflater=LayoutInflater.from(this);

        List<Rutina> rutinas = new ArrayList<>();
        rutinas.add(new Rutina("Pecho y Tríceps", "Fuerza y volumen", R.drawable.gym));
        rutinas.add(new Rutina("Espalda y Bíceps", "Espalda ancha",  R.drawable.gym));
        rutinas.add(new Rutina("Piernas", "Ponte fuerte abajo",  R.drawable.gym));
        rutinas.add(new Rutina("Hombros", "Definición total",  R.drawable.gym));
        rutinas.add(new Rutina("Piernas", "Ponte fuerte abajo",  R.drawable.gym));
        rutinas.add(new Rutina("Hombros", "Definición total",  R.drawable.gym));
        rutinas.add(new Rutina("Piernas", "Ponte fuerte abajo",  R.drawable.gym));
        rutinas.add(new Rutina("Hombros", "Definición total",  R.drawable.gym));

        cargarTarjetas(rutinas);
    }

    private void cargarTarjetas(List<Rutina> rutinas) {
        tableLayout.removeAllViews();

        for (int i = 0; i < rutinas.size(); i += 2) {
            TableRow row = new TableRow(this);

            // Carga la primera tarjeta
            View card1 = crearCard(rutinas.get(i));
            row.addView(card1);

            // Carga la segunda tarjeta si existe
            if (i + 1 < rutinas.size()) {
                View card2 = crearCard(rutinas.get(i + 1));
                row.addView(card2);
            } else {
                // Si es impar, rellena con un espacio en blanco
                View emptyView = new View(this);
                emptyView.setLayoutParams(new TableRow.LayoutParams(0, 0, 1));
                row.addView(emptyView);
            }

            tableLayout.addView(row);
        }
    }

    private View crearCard(Rutina rutina) {
        View card = inflater.inflate(R.layout.rutina_card, null);

        TextView titulo = card.findViewById(R.id.txtTituloCard);
        TextView descripcion = card.findViewById(R.id.txtDescCard);
        ImageView imagen = card.findViewById(R.id.imgRutina);

        titulo.setText(rutina.titulo);
        descripcion.setText(rutina.descripcion);
        imagen.setImageResource(rutina.imagenResId);

        // Puedes añadir un onClickListener aquí si quieres que abra otra vista
        card.setOnClickListener(v -> {
            Toast.makeText(this, "Rutina: " + rutina.titulo, Toast.LENGTH_SHORT).show();
            // Aquí podrías cargar otro layout dinámicamente o abrir un detalle
        });

        return card;
    }

    // Clase de ejemplo para manejar rutinas
    class Rutina {
        String titulo;
        String descripcion;
        int imagenResId;

        Rutina(String titulo, String descripcion, int imagenResId) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.imagenResId = imagenResId;
        }
    }
}