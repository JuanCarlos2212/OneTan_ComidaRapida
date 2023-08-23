package com.onetan.carlosevalparcial.ui.configuracion_repartidor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.onetan.carlosevalparcial.ConfiguracionIdioma;
import com.onetan.carlosevalparcial.ConfiguracionTamFuente;
import com.onetan.carlosevalparcial.R;

public class ConfiguracionRepartidorFragment extends Fragment {

    TextView txtcambiarIdioma, txtTemaOscuro, txttamFuente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_configuracion_repartidor,container,false);
        txtcambiarIdioma= vista.findViewById(R.id.cambiarIdiomaRepartidor);
        txtTemaOscuro= vista.findViewById(R.id.cambiartemaRepartidor);
        txttamFuente= vista.findViewById(R.id.cambiartamFuenteRepartidor);

        txtcambiarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConfiguracionIdioma.class);
                startActivity(intent);

            }
        });

//        txtTemaOscuro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), ConfiguracionTemaOscuro.class);
//                startActivity(intent);
//
//            }
//        });

        txttamFuente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConfiguracionTamFuente.class);
                startActivity(intent);

            }
        });
        return vista;

    }
}