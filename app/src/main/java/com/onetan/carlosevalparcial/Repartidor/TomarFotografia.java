package com.onetan.carlosevalparcial.Repartidor;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.MainActivityRepartidor;
import com.onetan.carlosevalparcial.R;
import com.onetan.carlosevalparcial.Util.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class TomarFotografia extends AppCompatActivity {

    Button btncamara,btnregistrarfoto;
    ImageView img;
    Bitmap imgBitmap;
    String ActualURL= Util.RUTA + "pedidoentregado.php";
    String rutaimagen, reciidpedido;
    String KEY_IMAGE = "foto";
    String KEY_NOMBRE = "nombreentrega";
    String KEY_IDPEDIDO = "idpedido";
EditText brevedescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_fotografia);
        getSupportActionBar().hide();
        btncamara = findViewById(R.id.BtnCapturarImagen);
        img = findViewById(R.id.imgfotoCapturada);
        brevedescripcion = findViewById(R.id.edtbreveDescripcion);
        btnregistrarfoto = findViewById(R.id.btnEntregaExitosa);
//        txtrutaarchivo = findViewById(R.id.rutaArchivo);



        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
        btnregistrarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reciidpedido = getIntent().getStringExtra("idpedidos");
                subiraBD(reciidpedido);
                mensajeexitoso();
//                Toast.makeText(TomarFotografia.this, "¡Imagen registrada correctamente!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void mensajeexitoso() {
        final Dialog mensajeexitoso = new Dialog(this);
        mensajeexitoso.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mensajeexitoso.setContentView(R.layout.dialog_felicidades_entregahecha);
        Button btnAceptar = (Button) mensajeexitoso.findViewById(R.id.btnAceptarEntregaExitosa);



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mensajeexitoso.getContext(), MainActivityRepartidor.class);
                startActivity(intent);
                mensajeexitoso.dismiss();
            }
        });
        mensajeexitoso.show();
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;

        try {
            imagenArchivo= crearimagen();
        }catch (IOException ex){
            Log.e("error",ex.toString());
        }
        if (imagenArchivo != null){
            Uri fotoUri = FileProvider.getUriForFile(this,"com.onetan.carlosevalparcial.fileprovider",imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode ==1 && resultCode == RESULT_OK){
            imgBitmap = BitmapFactory.decodeFile(rutaimagen);
            img.setImageBitmap(imgBitmap);
//            txtrutaarchivo.setText(rutaimagen);
            btncamara.setVisibility(View.GONE);
            btnregistrarfoto.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private File crearimagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen,".jpg",directorio);

        rutaimagen = imagen.getAbsolutePath();
        return imagen;
    }
    private void subiraBD(String recibeidpedido) {

        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ActualURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(TomarFotografia.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(TomarFotografia.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String imagen = getStringImagen(imgBitmap);
                String nombre = brevedescripcion.getText().toString();
                String idpedido = recibeidpedido;

                Map<String, String> params = new Hashtable<String, String>();
                params.put(KEY_IMAGE, imagen);
                params.put(KEY_NOMBRE, nombre);
                params.put(KEY_IDPEDIDO, idpedido);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}