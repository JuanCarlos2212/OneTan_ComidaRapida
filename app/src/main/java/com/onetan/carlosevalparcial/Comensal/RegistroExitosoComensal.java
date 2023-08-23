package com.onetan.carlosevalparcial.Comensal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.MainActivity;
import com.onetan.carlosevalparcial.R;
import com.onetan.carlosevalparcial.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistroExitosoComensal extends AppCompatActivity {
    Button btnEmpezar;
    String stremail,strpassword;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_exitoso_comensal);
        //no actionbar
        getSupportActionBar().hide();


        btnEmpezar = findViewById(R.id.btnEmpezarReExitoso);


        guardarpreferencias();

        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroExitosoComensal.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void guardarpreferencias() {
        stremail = getIntent().getStringExtra("correoelectronico");
        strpassword = getIntent().getStringExtra("contraseña");

        String URLusuario = Util.RUTA +"buscarpedidointer.php?correoelectronico="+stremail+"&contraseña="+strpassword;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLusuario, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    SharedPreferences preferences=getSharedPreferences("UsuarioLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    try {


                        jsonObject = response.getJSONObject(i);
                        String idusuario = jsonObject.getString("idusuario");
                        String nombres = jsonObject.getString("nombres");
                        String apmaterno = jsonObject.getString("apmaterno");
                        String appaterno = jsonObject.getString("appaterno");
                        String documentoidentidad = jsonObject.getString("documentoidentidad");
                        String celular = jsonObject.getString("celular");
                        String correoelectronico = jsonObject.getString("correoelectronico");
                        String contraseña = jsonObject.getString("contraseña");
                        String idrol = jsonObject.getString("idrol");


                        editor.putString("usuario",stremail);
                        editor.putString("password",strpassword);
                        editor.putString("idusuario",idusuario);
                        editor.putString("nombres",nombres);
                        editor.putString("apmaterno",apmaterno);
                        editor.putString("appaterno",appaterno);
                        editor.putString("documentoidentidad",documentoidentidad);
                        editor.putString("celular",celular);
                        editor.putString("correoelectronico",correoelectronico);
                        editor.putString("contraseña",contraseña);
                        editor.putBoolean("session",true);
                        editor.commit();

                    } catch (JSONException e) {
                        Toast.makeText(RegistroExitosoComensal.this, "esta entrando al catch", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroExitosoComensal.this, "Error en la conexion", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}