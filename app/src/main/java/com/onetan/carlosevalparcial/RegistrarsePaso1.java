
package com.onetan.carlosevalparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarsePaso1 extends AppCompatActivity {

    EditText edtNombres, edtapMaterno, edtapPaterno, edtCelular,edtDocumento;
    Spinner spElecDocumento;
    Button btnSiguente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse_paso1);
        ////para que no aparesca la barra de accion
        getSupportActionBar().hide();

        edtNombres = findViewById(R.id.edtNombresRe1);
        edtapMaterno = findViewById(R.id.edtapMaternoRe1);
        edtapPaterno = findViewById(R.id.edtAppaternoRe1);
        edtCelular = findViewById(R.id.edtCelularRe1);
        edtDocumento = findViewById(R.id.edtDocumentoRe1);
        spElecDocumento = findViewById(R.id.spinnerRe1);
        btnSiguente = findViewById(R.id.btnSiguienteRe1);



        String tipousuario = getIntent().getStringExtra("claveusuario");

        btnSiguente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombres = edtNombres.getText().toString();
                String apMaterno = edtapMaterno.getText().toString();
                String apPaterno = edtapPaterno.getText().toString();
                String Documento = edtDocumento.getText().toString();
                String Celular = edtCelular.getText().toString();

                if(     nombres.isEmpty() ||
                        apMaterno.isEmpty() ||
                        apPaterno.isEmpty() ||
                        Documento.isEmpty() ||
                        Celular.isEmpty()
                ){
                    Toast.makeText(RegistrarsePaso1.this, "No se aceptan campos vacios", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(RegistrarsePaso1.this,RegistrarsePaso2.class);
                    intent.putExtra("claveusuario",tipousuario);
                    intent.putExtra("nombres",nombres);
                    intent.putExtra("apMaterno",apMaterno);
                    intent.putExtra("apPaterno",apPaterno);
                    intent.putExtra("Documento",Documento);
                    intent.putExtra("Celular",Celular);
                    startActivity(intent);
                }

            }
        });
    }
}