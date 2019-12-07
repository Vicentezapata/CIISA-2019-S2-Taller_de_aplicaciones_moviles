package com.example.gymfitnees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                if (pass.equals("123") && user.equals("admin")){
                    Intent intent = new Intent(v.getContext(),ConsultarEvaluaciones.class);
                    intent.putExtra("nombreUsuario",user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(v.getContext(),"Usurio no permitido", Toast.LENGTH_LONG).show();
                }

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
}
