package com.onetan.carlosevalparcial.Comensal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.onetan.carlosevalparcial.R;

public class DetalledeComida extends AppCompatActivity {

    Button btnComprarDetalle;
    Bitmap bitmap;
    ImageView imgdetalle;
    TextView preciodet, descripdet, nombredet;
    Platillo platillo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallede_comida);
        getSupportActionBar().hide();

        imgdetalle = findViewById(R.id.imgComidaDetalle);
        preciodet = findViewById(R.id.txtPrecioDetalle);
        descripdet = findViewById(R.id.txtDescripDetalle);
        nombredet = findViewById(R.id.txtnombreComidaDetalle);

        btnComprarDetalle = findViewById(R.id.btnComprarDetalle);

        Bundle recibenommre = getIntent().getExtras();
        String nombre = recibenommre.getString("keynombres");
        nombredet.setText(nombre);

        Bundle recibedescripcion = getIntent().getExtras();
        String descripcion = recibedescripcion.getString("Keydescripcion");
        descripdet.setText(descripcion);

        Bundle recibeprecio = getIntent().getExtras();
        String precio = recibeprecio.getString("keyprecio");
        preciodet.setText(precio);

        Bundle recibeimagen = getIntent().getExtras();
        String imagen = recibeimagen.getString("keyimagen");

        try {
            byte[] bytecode = Base64.decode(imagen, Base64.DEFAULT);
            Bitmap imagen2 = BitmapFactory.decodeByteArray(bytecode, 0, bytecode.length);
            imgdetalle.setImageBitmap(imagen2);

        } catch (Exception e) {
            e.printStackTrace();

        }
        Bundle recibeidplatillo = getIntent().getExtras();
        String idplatillo = recibeidplatillo.getString("keyidplatillo");

        btnComprarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalledeComida.this,UbicaciondelComensal.class);
                intent.putExtra("keyidplatillo",idplatillo);
                intent.putExtra("keyprecio",precio);
                startActivity(intent);

            }
        });
    }

}