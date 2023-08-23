package com.onetan.carlosevalparcial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.Comensal.RegistroExitosoComensal;
import com.onetan.carlosevalparcial.Repartidor.RegistroExitoso;
import com.onetan.carlosevalparcial.Util.Util;

import org.json.JSONObject;

public class RegistrarsePaso2 extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText edtCorreo, edtContra;
    Button btnSiguente;

    ProgressDialog progreso;
    RequestQueue requestQueue; //cola de requerimiento tiene que saber esperar, gestiona el hecho que espere las respuestas del servidor
    JsonObjectRequest jsonObjectRequest;
    

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse_paso2);
        //no actionbar
        getSupportActionBar().hide();

        btnSiguente = findViewById(R.id.btnSiguienteRe2);
        edtCorreo = findViewById(R.id.edtCorreoeleR2);
        edtContra = findViewById(R.id.edtContraseñaR2);

        requestQueue = Volley.newRequestQueue(this);


        String tipousuario = getIntent().getStringExtra("claveusuario");
        String nombres = getIntent().getStringExtra("nombres");
        String apMaterno = getIntent().getStringExtra("apMaterno");
        String apPaterno = getIntent().getStringExtra("apPaterno");
        String Documento = getIntent().getStringExtra("Documento");
        String Celular = getIntent().getStringExtra("Celular");

        btnSiguente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correo = edtCorreo.getText().toString();
                String contraseña = edtCorreo.getText().toString();

                if (correo.isEmpty()||contraseña.isEmpty()){
                    Toast.makeText(RegistrarsePaso2.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();

                }else{
                    if(tipousuario.equals("1")){
                        RegistrarUsuario(nombres,apMaterno,apPaterno,Documento,Celular,tipousuario);
                        String correoenvi = edtCorreo.getText().toString();
                        String contraenv = edtContra.getText().toString();
                        Toast.makeText(RegistrarsePaso2.this, "Registrado Exitosamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrarsePaso2.this, RegistroExitosoComensal.class);
                        intent.putExtra("correoelectronico",correoenvi);
                        intent.putExtra("contraseña",contraenv);
                        startActivity(intent);
                    }else{
//                        Intent intent = new Intent(RegistrarsePaso2.this, RegistroExitoso.class);
//                        startActivity(intent);
                        RegistrarUsuario(nombres,apMaterno,apPaterno,Documento,Celular,tipousuario);
                        String correoenvi = edtCorreo.getText().toString();
                        String contraenv = edtContra.getText().toString();
                        Toast.makeText(RegistrarsePaso2.this, "Registrado Exitosamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrarsePaso2.this, RegistroExitoso.class);
                        intent.putExtra("correoelectronico",correoenvi);
                        intent.putExtra("contraseña",contraenv);
                        startActivity(intent);
                    }
                }
                
            }
        });

    }



    private void RegistrarUsuario(String nombres,String apMaterno,String apPaterno,String Documento,String Celular,String tipousuario) {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Registrando Usuario");
        progreso.show();

        String correo = edtCorreo.getText().toString();
        String contra = edtContra.getText().toString();

        String url = Util.RUTA + "registrarUsuario.php?nombres=" + nombres + "&apmaterno="+apMaterno+"&appaterno=" + apPaterno +
                "&documentoidentidad="+Documento+"&celular="+Celular+"&correoelectronico="+correo+"&contraseña="+contra+"&idrol="+tipousuario;

        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(RegistrarsePaso2.this, "Error de Inserción", Toast.LENGTH_SHORT).show();
        Log.i("error",error.toString());
    }
}