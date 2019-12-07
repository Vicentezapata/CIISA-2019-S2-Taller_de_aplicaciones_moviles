package com.example.gymfitnees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ConsultarEvaluaciones extends AppCompatActivity {
    TextInputLayout tilFecha,tilPeso;
    Button btnAniadirReg;
    ListView lvDatos;
    String [] datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_evaluaciones);

        String nomUser = getIntent().getStringExtra("nombreUsuario");

        tilFecha = findViewById(R.id.tilFechaCon);
        tilPeso = findViewById(R.id.tilPesoCon);
        btnAniadirReg = findViewById(R.id.btnAniadirReg);
        lvDatos = findViewById(R.id.lvDatos);

        datos =  listar();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lvDatos.setAdapter(adapter);

        btnAniadirReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = tilFecha.getEditText().getText().toString();
                String imc = tilPeso.getEditText().getText().toString();
                insertarRegistro(imc,fecha,1);
                datos =  listar();
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,datos);
                lvDatos.setAdapter(adapter);

            }
        });
        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),EliminarEvaluaciones.class);
                intent.putExtra("id",datos[position].split(";")[0]);
                intent.putExtra("texto",datos[position].split(";")[1]);
                startActivity(intent);
            }
        });







    }
    public void insertarRegistro(String imc, String fecha, int id_user){
        DBhelper dBhelper = new DBhelper(this,"gymFitness_desa",null,1);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        if(db != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("imc",imc);
            contentValues.put("fecha",fecha);
            contentValues.put("id_usuario",id_user);
            long nFila = db.insert("tbl_evaluaciones",null,contentValues);
            if(nFila>0){
                Toast.makeText(this, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No se pudo guardar el registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String[]  listar(){
        String[] datos = new String[0];
        DBhelper dBhelper = new DBhelper(this,"gymFitness_desa",null,1);
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        if(db != null) {
            Cursor cur = db.rawQuery("SELECT * FROM  tbl_evaluaciones",null);
            int cantidad = cur.getCount();
            int i = 0;
            datos = new String[cantidad];
            if(cur.moveToFirst()){
                do {
                    String fila = cur.getString(0)+";FECHA: "+cur.getString(1)+" IMC: "+cur.getString(2);
                    datos[i] = fila;
                    i++;
                }while (cur.moveToNext());
            }
        }
        return datos;
    }


    //LIFECYCLE ACTIVITIES
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart()");
        listar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","onRestart()");
    }
}
