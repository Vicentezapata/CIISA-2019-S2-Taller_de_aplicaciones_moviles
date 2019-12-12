package com.example.gymfitnees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout tilUser,tilPass;
    Button btnLogin;
    TextView txtRegister;
    String estatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REFERENCIAS ID
        tilUser = findViewById(R.id.tilCorreo);
        tilPass = findViewById(R.id.tilPass);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        //BTN LOGIN
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =  tilUser.getEditText().getText().toString();
                String pass = tilPass.getEditText().getText().toString();
                login(user,pass);
                /*if (pass.equals("123") && user.equals("admin")){
                    Intent intent = new Intent(v.getContext(),ConsultarEvaluaciones.class);
                    intent.putExtra("nombreUsuario",user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(v.getContext(),"Usurio no permitido", Toast.LENGTH_LONG).show();
                }*/

            }
        });

        //BTN REGISTRO
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RegistroUsuario.class);
                startActivity(intent);
            }
        });




    }
    public void login(String nomUser, String clave){
        DBhelper dBhelper = new DBhelper(this,"gymFitness_desa",null,1);
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        if(db != null) {
            Cursor cur = db.rawQuery("SELECT * FROM  tbl_usuarios where nomUser = '"+nomUser+"' and clave = '"+clave+"' ",null);
            int cantidad = cur.getCount();
            Toast.makeText(this, "CANTI:"+cantidad, Toast.LENGTH_SHORT).show();
            if(cantidad == 1){
                if(cur.moveToFirst()){
                    do {
                        estatura = cur.getString(5);
                    }while (cur.moveToNext());
                }
                Intent intent =  new Intent(this,ConsultarEvaluaciones.class);
                intent.putExtra("estaturaUser",estatura);
                startActivity(intent);

            }

        }
        Toast.makeText(this, "ESTATURA = "+estatura, Toast.LENGTH_SHORT).show();
    }
}
