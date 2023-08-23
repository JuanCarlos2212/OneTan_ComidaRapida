package com.onetan.carlosevalparcial.ui.micuenta;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.onetan.carlosevalparcial.R;

public class MiCuentaFragment extends Fragment {
    TextView nombre,celular;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mi_cuenta,container,false);
        nombre = vista.findViewById(R.id.micuentanombres);
        celular = vista.findViewById(R.id.micuentacelular);
        cargarpreferencias();

        return vista;
    }
    public void cargarpreferencias(){
        SharedPreferences preferences= getActivity().getSharedPreferences("UsuarioLogin",0);
        String nom= preferences.getString("nombres","no hay nada");
        String app= preferences.getString("appaterno","no hay nada");
        String apm= preferences.getString("apmaterno","no hay nada");
        nombre.setText(nom + " "+app+" "+apm);
        celular.setText("+51 " +preferences.getString("celular","no hay nada"));

    }

}