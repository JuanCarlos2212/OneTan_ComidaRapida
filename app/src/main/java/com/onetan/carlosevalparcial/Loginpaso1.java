package com.onetan.carlosevalparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Loginpaso1 extends AppCompatActivity {

    Button btnInicioSes;
    TextView txtregistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpaso1);

        ////para que no aparesca la barra de accion
        getSupportActionBar().hide();
        btnInicioSes = findViewById(R.id.btnIniciarSesionLog1);
        txtregistrate = findViewById(R.id.txtRegistrateLog1);

        String tipousuario = getIntent().getStringExtra("claveusuario");



        btnInicioSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loginpaso1.this,IniciarSesion.class);
                intent.putExtra("claveusuario",tipousuario);
               startActivity(intent);
            }
        });

        txtregistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loginpaso1.this,RegistrarsePaso1.class);
                intent.putExtra("claveusuario",tipousuario);
                startActivity(intent);
            }
        });
    }

}