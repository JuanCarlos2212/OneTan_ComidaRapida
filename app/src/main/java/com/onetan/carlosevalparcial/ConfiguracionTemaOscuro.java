package com.onetan.carlosevalparcial;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ConfiguracionTemaOscuro extends AppCompatActivity {

    RadioButton temaclaro, temaOscuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_tema_oscuro);
        ////para que no aparesca la barra de accion
        getSupportActionBar().hide();
        temaclaro = findViewById(R.id.radioTemaClaro);
        temaOscuro = findViewById(R.id.radioTemaOscuro);

        temaclaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }
        });
        temaOscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

    }

}