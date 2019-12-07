package com.example.gymfitnees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class EliminarEvaluaciones extends AppCompatActivity {
    TextView txtDato;
    String idDato;
    String fecha;
    String imc;
    Button btnBorrar;
    Button btnActualizar;
    TextInputLayout tilFecha,tilImc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_evaluaciones);
        String datoEvaluacion = getIntent().getStringExtra("texto");

        fecha = datoEvaluacion.split(" ")[1];
        imc = datoEvaluacion.split(" ")[3];

        idDato = getIntent().getStringExtra("id");
        Log.i("ID",idDato);

        //REFERENCIAS
        btnBorrar = findViewById(R.id.btnBorrar);
        btnActualizar = findViewById(R.id.btnAct);
        tilFecha = findViewById(R.id.tilFechaAct);
        tilImc = findViewById(R.id.tilPesoAct);

        //SETEAMOS LOS CAMPOS DEL LAYOUT
        tilFecha.getEditText().setText(fecha);
        tilImc.getEditText().setText(imc);

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarRegistro(idDato);
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imcAct = tilImc.getEditText().getText().toString();
                String fechaAct = tilFecha.getEditText().getText().toString();
                actualizarRegistro(idDato,imcAct,fechaAct);
            }
        });

        txtDato = findViewById(R.id.txtDato);
        txtDato.setText(datoEvaluacion);
    }

    public void borrarRegistro(String id){
        DBhelper dBhelper = new DBhelper(this,"gymFitness_desa",null,1);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        if(db != null){
            int exect = db.delete("tbl_evaluaciones","id = "+id,null);
            if (exect>0){
                Intent intent = new Intent(this,ConsultarEvaluaciones.class);
                Toast.makeText(this, "Registro borrado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else{
                Toast.makeText(this, "No se pudo borrar el registro ", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void actualizarRegistro(String id, String imc,String fecha){
        DBhelper dBhelper = new DBhelper(this,"gymFitness_desa",null,1);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        if(db != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("fecha",fecha);
            contentValues.put("imc",imc);
            int exect = db.update("tbl_evaluaciones",contentValues,"id = "+id,null);
            if (exect>0){
                Intent intent = new Intent(this,ConsultarEvaluaciones.class);
                Toast.makeText(this, "Registro actualizado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "No se pudo actualizar el registro ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
