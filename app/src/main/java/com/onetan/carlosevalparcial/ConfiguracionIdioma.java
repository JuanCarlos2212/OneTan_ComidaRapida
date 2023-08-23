package com.onetan.carlosevalparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ConfiguracionIdioma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_idioma);
        ////para que no aparesca la barra de accion
        getSupportActionBar().hide();
    }
}