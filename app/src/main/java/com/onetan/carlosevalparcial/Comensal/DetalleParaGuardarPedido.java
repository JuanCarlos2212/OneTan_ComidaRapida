package com.onetan.carlosevalparcial.Comensal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.MainActivity;
import com.onetan.carlosevalparcial.R;
import com.onetan.carlosevalparcial.Util.Util;

import org.json.JSONObject;

public class DetalleParaGuardarPedido extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    Button btnAceptarPedido, BtnCancelarPedido;
    TextView  cliente, celular, pagar;

    String recibeprecio,recibeidplatillo;

    Double mlati,mlongi;
    ProgressDialog progreso;
    RequestQueue requestQueue; //cola de requerimiento tiene que saber esperar, gestiona el hecho que espere las respuestas del servidor
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_para_guardar_pedido);
        getSupportActionBar().hide();

        btnAceptarPedido = findViewById(R.id.AceptarguardarenBD);
        cliente = findViewById(R.id.datoCorrectoCliente);
        BtnCancelarPedido = findViewById(R.id.CancelarTodoelpedido);
        celular = findViewById(R.id.datoCorrectoCelular);
        pagar = findViewById(R.id.correctototalcancelar);

        recibeprecio = getIntent().getStringExtra("keyprecio");
        recibeidplatillo = getIntent().getStringExtra("keyidplatillo");



        requestQueue = Volley.newRequestQueue(this);
        SharedPreferences preferences = getSharedPreferences("UsuarioLogin",0);
        String idusuario = preferences.getString("idusuario","0");
        String nom= preferences.getString("nombres","no hay nada");
        String app= preferences.getString("appaterno","no hay nada");
        String apm= preferences.getString("apmaterno","no hay nada");
        cliente.setText(nom + " "+app+" "+apm);
        celular.setText("+51 " +preferences.getString("celular","no hay nada"));
        pagar.setText(recibeprecio);
        mlati = getIntent().getDoubleExtra("mLat",1);
        mlongi = getIntent().getDoubleExtra("mLong",1);


        btnAceptarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regispedidotrarenbd(idusuario);


            }
        });
        BtnCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleParaGuardarPedido.this, MainActivity.class);
                Toast.makeText(DetalleParaGuardarPedido.this, "Pedido Cancelado", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void regispedidotrarenbd(String idusuario) {


        progreso = new ProgressDialog(this);
        progreso.setMessage("insertando");
        progreso.show();

        String url = Util.RUTA + "registrarpedido.php?latitud=" + mlongi + "&longitud="+mlati+"&idplatillo="
                + recibeidplatillo+"&idusuario="+idusuario;

       url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);

        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this, "Registrado Exitosamente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DetalleParaGuardarPedido.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "Error de Inserción", Toast.LENGTH_SHORT).show();
        Log.i("error",error.toString());
    }
}