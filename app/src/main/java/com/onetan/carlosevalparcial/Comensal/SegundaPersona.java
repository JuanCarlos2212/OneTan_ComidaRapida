package com.onetan.carlosevalparcial.Comensal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.onetan.carlosevalparcial.R;

public class SegundaPersona extends AppCompatActivity {
 Button ConfirmarPedidoSegundaPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_persona);
        getSupportActionBar().hide();
        ConfirmarPedidoSegundaPersona = findViewById(R.id.ConfirmarPedidoSegundaPersona);

        Intent i = getIntent();
        Double lon = i.getExtras().getDouble("longitud");
        Double lat = i.getExtras().getDouble("latitud");

        ConfirmarPedidoSegundaPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                pedidoconfirmado();
            }
        });
    }

//    private void pedidoconfirmado() {
//        final Dialog pedidoconfirmado = new Dialog(this);
//        pedidoconfirmado.setContentView(R.layout.dialog_pedido_registrado_exitosamente);
//        Button btnSiguiente = (Button) pedidoconfirmado.findViewById(R.id.btnAceptarPedidoExitoso);
//
//
//
//        btnSiguiente.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(pedidoconfirmado.getContext(), MainActivity.class);
//                startActivity(intent);
//                pedidoconfirmado.dismiss();
//            }
//        });
//        pedidoconfirmado.show();
//    }
}