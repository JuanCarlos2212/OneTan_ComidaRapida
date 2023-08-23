package com.onetan.carlosevalparcial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IniciarSesion extends AppCompatActivity {

    Button btnInicioSes;
    EditText edtCorreo, edtContra;

    RequestQueue requestQueue;

    String stremail, strpassword;
    String url = Util.RUTA +"logear.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        getSupportActionBar().hide();

        btnInicioSes = findViewById(R.id.btniniciarsesionGo);
        edtCorreo = findViewById(R.id.edtCorreoElecGo);
        edtContra = findViewById(R.id.edtContrasenaGo);

        String tipousuario = getIntent().getStringExtra("claveusuario");


        cargarpreferencias();

        btnInicioSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contra = edtContra.getText().toString();
                String correo = edtCorreo.getText().toString();
                if(contra.isEmpty()||correo.isEmpty()){

                    Toast.makeText(IniciarSesion.this, "No se permiten campos vacios", Toast.LENGTH_SHORT).show();

                }else {
                    if (tipousuario.equals("2")) {

//                        Intent intent = new Intent(IniciarSesion.this, MainActivityRepartidor.class);
//                        startActivity(intent);
                        validadDatos(tipousuario);

                    } else {
                        validadDatos(tipousuario);

                    }


                }
            }
        });

    }

    private void validadDatos(String tipousuario) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("por favor esperaa...");
        progressDialog.show();
        stremail = edtCorreo.getText().toString();
        strpassword = edtContra.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (response.equalsIgnoreCase("Bienvenido")) {
                    if(tipousuario.equals("1")){
                        Intent intent = new Intent(IniciarSesion.this, MainActivity.class);
                        guardarpreferencias();
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(IniciarSesion.this, MainActivityRepartidor.class);
                        guardarpreferencias();
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(IniciarSesion.this, response, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(IniciarSesion.this, "Email o Password incorrecto", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("correoelectronico",stremail);
                params.put("contraseña",strpassword);
                params.put("idrol",tipousuario);
                return params;

            }

        };
        requestQueue = Volley.newRequestQueue(IniciarSesion.this);
        requestQueue.add(request);

    }

    private void guardarpreferencias() {
        stremail = edtCorreo.getText().toString();
        strpassword = edtContra.getText().toString();

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
                        Toast.makeText(IniciarSesion.this, "esta entrando al catch", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IniciarSesion.this, "Error en la conexion", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
    public void cargarpreferencias(){
        SharedPreferences preferences=getSharedPreferences("UsuarioLogin", Context.MODE_PRIVATE);
        edtCorreo.setText(preferences.getString("usuario","sega@gmail.com"));
        edtContra.setText(preferences.getString("password","sega123"));

    }


}