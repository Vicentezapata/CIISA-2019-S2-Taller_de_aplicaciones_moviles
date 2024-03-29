package com.example.gymfitnees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegistroUsuario extends AppCompatActivity {
    TextInputLayout tilNomUser,tilNombre,tilApellido,tilFchNac,tilEstat,tilClave;
    private int mYear,mMonth,mDay;
    String nomUser,nombre,apellido,fchNac,estatura,clave;
    Button btnCalendar,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        //REFERENCIAS
        tilNomUser = findViewById(R.id.tilNomUser);
        tilNombre = findViewById(R.id.tilNombre);
        tilApellido = findViewById(R.id.tilApellido);
        tilFchNac = findViewById(R.id.tilFchNac);
        tilEstat = findViewById(R.id.tilEstat);
        tilClave = findViewById(R.id.tilClave);
        btnCalendar = findViewById(R.id.btnCalendar);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomUser = tilNomUser.getEditText().getText().toString();
                nombre = tilNombre.getEditText().getText().toString();
                apellido = tilApellido.getEditText().getText().toString();
                fchNac = tilFchNac.getEditText().getText().toString();
                estatura = tilEstat.getEditText().getText().toString();
                clave = tilClave.getEditText().getText().toString();
                if (nomUser.length()>0 && nombre.length()>0 && apellido.length()>0 && fchNac.length()>0  && estatura.length()>0  && clave.length()>0 ){
                    insertarUsuario(nomUser,nombre,apellido,fchNac,estatura,clave);
                }
                if (nomUser.length()==0){
                    tilNomUser.setError("NOMBRE REQUERIDO");
                }
                if (nombre.length()==0){
                    tilNombre.setError("USUARIO REQUERIDO");
                }
                if (fchNac.length()==0){
                    tilFchNac.setError("FECH NAC REQUERIDO");
                }
                if (apellido.length()==0){
                    tilApellido.setError("APELLIDO REQUERIDO");
                }
                if (estatura.length()==0){
                    tilEstat.setError("ESTATURA REQUERIDO");
                }
                if (clave.length()==0){
                    tilClave.setError("CLAVE REQUERIDO");
                }
            }
        });
        final Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tilFchNac.getEditText().setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });




    }
    public void insertarUsuario(String nomUser, String nombre, String apellido, String fechNac, String estatura, String clave ) {
        DBhelper dBhelper = new DBhelper(this, "gymFitness_desa", null,1);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        if(db != null){
            ContentValues contentValues =new ContentValues();
            contentValues.put("nomUser",nomUser);
            contentValues.put("nombre",nombre);
            contentValues.put("apellido",apellido);
            contentValues.put("fechNac",fechNac);
            contentValues.put("estatura",estatura);
            contentValues.put("clave",clave);
            long nFila = db.insert("tbl_usuarios",null,contentValues);
            if(nFila>0){
                Toast.makeText(this, "Usuario registrado exitosamente ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
