package com.onetan.carlosevalparcial.ui.micuenta_repartidor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.onetan.carlosevalparcial.R;


public class MicuentaRepartidorFragment extends Fragment {

    TextView nombrere,celularre;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_micuenta_repartidor,container,false);

        nombrere = vista.findViewById(R.id.micuentarepartidornombre);
        celularre = vista.findViewById(R.id.micuentarepartidorcelular);
        cargarpreferencias();
        return vista;

    }
    public void cargarpreferencias(){
        SharedPreferences preferences= getActivity().getSharedPreferences("UsuarioLogin",0);
        String nom= preferences.getString("nombres","no hay nada");
        String app= preferences.getString("appaterno","no hay nada");
        String apm= preferences.getString("apmaterno","no hay nada");
        nombrere.setText(nom + " "+app+" "+apm);
        celularre.setText("+51 " +preferences.getString("celular","no hay nada"));

    }
}