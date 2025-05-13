package com.powerroutine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.powerroutine.Componets.Navegator;
import com.powerroutine.Static.EjercicesStatic;
import com.powerroutine.Static.RutinesListStatic;
import com.powerroutine.Static.UserStatic;
import com.powerroutine.form.LoginValidate;
import com.powerroutine.model.EjerciceModel;
import com.powerroutine.model.RutineModel;
import com.powerroutine.model.UserModel;
import com.powerroutine.service.UserService;

public class PerfilActivity extends AppCompatActivity {
    private UserModel user,userOptional;
    private UserService userService=new UserService();
    private TextView txtGoalUser,txtLeveUser,txtDaysUser,txtNameRoutine,txtRoutineDetails;
    private EditText txtNameUser,txtEmailUser;
    private Button btnSaveEdit,btnSaveUser;
    private boolean isSafeSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        user= UserStatic.user;
        userOptional= UserStatic.user;
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        new Navegator(btnHome,btnPerfil,btnCalendar,this,"perfil");

        txtNameUser=findViewById(R.id.txtNameUser);
        txtNameUser.setEnabled(false);
        txtEmailUser=findViewById(R.id.txtEmailUser);
        txtEmailUser.setEnabled(false);
        txtGoalUser=findViewById(R.id.txtGoalUser);
        txtLeveUser=findViewById(R.id.txtLeveUser);
        txtDaysUser=findViewById(R.id.txtDaysUser);
        txtNameRoutine=findViewById(R.id.txtNameRoutine);
        txtRoutineDetails=findViewById(R.id.txtRoutineDetails);
        btnSaveEdit=findViewById(R.id.btnSaveEdit);
        btnSaveUser=findViewById(R.id.btnSaveUser);

        isSafeSave= false;

        cargarDatos();

    }

    private void cargarDatos(){
        System.out.println("EMAIL: "+user.getEmail());

        txtNameUser.setText(user.getName());
        txtEmailUser.setText(user.getEmail());
        txtGoalUser.setText(user.getObjetiveString());
        txtLeveUser.setText(user.getLevelString());
        txtDaysUser.setText(user.getDaysWeek()+" dias a la semana");
        String name= RutinesListStatic.rutinas.getRutinas().get(0).getName();
        txtNameRoutine.setText(name);

        String details="";
        int contador=1;
        for(RutineModel rutina: RutinesListStatic.rutinas.getRutinas()){
            details+="• Rutina "+contador+": "+ rutina.getType()+"\n";
            details+="\t\tEjercicios: "+"\n";

            for (EjerciceModel ejercice : EjercicesStatic.ejerciceDTD.getEjercices()) {
                if (rutina.getIdEjercices().contains(ejercice.getId())) {
                    details += "\t\t\t\t -" + ejercice.getName()+"\n";
                }
            }
            details+="\n";
            contador++;
        }
        txtRoutineDetails.setText(details);


    }
    public void editName(View v){
        txtNameUser.setEnabled(true);
        txtNameUser.setBackgroundResource(R.drawable.rounded_border);
        txtNameUser.setTextColor(getResources().getColor(R.color.primary_color));
        btnSaveEdit.setBackgroundResource(R.drawable.button_background_orange);

    }
    public void editEmail(View v){
        txtEmailUser.setEnabled(true);
        txtEmailUser.setBackgroundResource(R.drawable.rounded_border);
        txtEmailUser.setTextColor(getResources().getColor(R.color.primary_color));
        btnSaveEdit.setBackgroundResource(R.drawable.button_background_orange);
    }
    public void saveTextUser(View v){
        try {
            userOptional.setName(txtNameUser.getText().toString());
            userOptional.setEmail(txtEmailUser.getText().toString());
            LoginValidate validate= new LoginValidate();
            if(validate.validateUpdate(userOptional)){
                btnSaveEdit.setBackgroundResource(R.drawable.button_background_dark);
                txtNameUser.setEnabled(false);
                txtNameUser.setBackgroundResource(0);
                txtNameUser.setTextColor(Color.WHITE);
                txtNameUser.setText(userOptional.getName());

                txtEmailUser.setEnabled(false);
                txtEmailUser.setBackgroundResource(0);
                txtEmailUser.setTextColor(Color.WHITE);
                txtEmailUser.setText(userOptional.getEmail());

                btnSaveUser.setBackgroundResource(R.drawable.button_background_green);
                isSafeSave=true;
            }else {
                if(txtNameUser.isEnabled()){
                    txtNameUser.setText("Datos invalidos");
                    txtNameUser.setTextColor(getResources().getColor(R.color.red));
                }
                if (txtEmailUser.isEnabled()){
                    txtEmailUser.setText("Datos invalidos");
                    txtEmailUser.setTextColor(getResources().getColor(R.color.red));
                }
            }
        }catch (Exception e){
            System.out.println("Error al guardar el usuario: "+e.getMessage());
        }
    }

    public void savePerfil(View V){
        if(isSafeSave){
         userService.updateUser(userOptional,this);
         btnSaveUser.setBackgroundResource(R.drawable.button_background_dark);
         isSafeSave= false;
        }
    }


    public void changePreferences(View v){
        Intent intent= new Intent(this, UserOpcionActivity.class);
        startActivity(intent);
        finish();
    }
    public void changeRutines(View v) {
        Intent intent = new Intent(this, RutineSelecetedActivity.class);
        startActivity(intent);
        finish();
    }
    public void deletePerfil(View v){
        Intent login=new Intent(this, LoginActivity.class);
        userService.deleteUser(user,this,login);
    }
    public void back(View v){
        finish();
    }

}