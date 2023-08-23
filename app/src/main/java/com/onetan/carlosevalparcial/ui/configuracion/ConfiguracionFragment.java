package com.onetan.carlosevalparcial.ui.configuracion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.onetan.carlosevalparcial.ConfiguracionIdioma;
import com.onetan.carlosevalparcial.ConfiguracionTamFuente;
import com.onetan.carlosevalparcial.ConfiguracionTemaOscuro;
import com.onetan.carlosevalparcial.R;


public class ConfiguracionFragment extends Fragment {

    TextView txtcambiarIdioma, txtTemaOscuro, txttamFuente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_configuracion,container,false);

        txtcambiarIdioma= vista.findViewById(R.id.txtcambiaridioma);
        txtTemaOscuro= vista.findViewById(R.id.txtTemaosucro);
        txttamFuente= vista.findViewById(R.id.txttamafuente);

        txtcambiarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ConfiguracionIdioma.class);
                startActivity(intent);

            }
        });

        txtTemaOscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConfiguracionTemaOscuro.class);
                startActivity(intent);

            }
        });

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