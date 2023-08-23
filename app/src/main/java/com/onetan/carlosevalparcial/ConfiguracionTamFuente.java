package com.onetan.carlosevalparcial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ConfiguracionTamFuente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_tam_fuente);
        ////para que no aparesca la barra de accion
        getSupportActionBar().hide();



    }
}