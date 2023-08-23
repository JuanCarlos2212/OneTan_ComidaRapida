package com.onetan.carlosevalparcial.Repartidor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onetan.carlosevalparcial.R;
import com.onetan.carlosevalparcial.databinding.ActivityDetalleParaEntregarBinding;

public class DetalleParaEntregar extends FragmentActivity implements OnMapReadyCallback {
    Button btnEmpezarmejorRuta;
    String destiNombre,destNombreplato,desCelular,desLongitud,desLatitud,desTotalpagar,desidpedido;
    TextView destinatarioNombre,destinatarioNombreplato,destinatarioCelular,destinatarioTotalpagar;

    private GoogleMap mMap;
    private ActivityDetalleParaEntregarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalleParaEntregarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        btnEmpezarmejorRuta = findViewById(R.id.btnEmpezarmejorRuta);
        destinatarioNombre = findViewById(R.id.nombreDestinatario);
        destinatarioNombreplato = findViewById(R.id.comidaDestinatario);
        destinatarioCelular = findViewById(R.id.celularDestinatario);
        destinatarioTotalpagar = findViewById(R.id.totalcobrarDestinatario);

        Bundle recibedatos = getIntent().getExtras();
        destiNombre = recibedatos.getString("nombreusuario");
        destNombreplato = recibedatos.getString("nombrecomida");
        desCelular = recibedatos.getString("celular");
        desLongitud = recibedatos.getString("longitud");
        desLatitud = recibedatos.getString("latitud");
        desTotalpagar = recibedatos.getString("precio");
        desidpedido = recibedatos.getString("idpedidos");


        destinatarioNombre.setText(destiNombre);
        destinatarioNombreplato.setText(destNombreplato);
        destinatarioCelular.setText("+51 "+desCelular);
        destinatarioTotalpagar.setText("S/." +desTotalpagar);

        Double lat = Double.valueOf(desLatitud);
        Double lon = Double.valueOf(desLongitud);




        LatLng unLugar = new LatLng(lon,lat);

        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marcadorpaquete)).position(unLugar).title("Para: "+destiNombre).snippet("Platillo: "+destNombreplato).anchor(0.5f,0.5f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unLugar));
        CameraPosition cameraPosition = CameraPosition.builder().target(unLugar).zoom(17).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        btnEmpezarmejorRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetalleParaEntregar.this,MejorRuta.class);
                intent.putExtra("nombrecomida",destNombreplato );
                intent.putExtra("nombreusuario",destiNombre);
                intent.putExtra("idpedidos", desidpedido);
                intent.putExtra("latitud", desLatitud);
                intent.putExtra("longitud", desLongitud);
                intent.putExtra("celular", desCelular);
                intent.putExtra("precio",desTotalpagar);
                startActivity(intent);

//                Toast.makeText(DetalleParaEntregar.this,destNombreplato , Toast.LENGTH_SHORT).show();
//                Toast.makeText(DetalleParaEntregar.this,destiNombre , Toast.LENGTH_SHORT).show();
//                Toast.makeText(DetalleParaEntregar.this,desidpedido , Toast.LENGTH_SHORT).show();
//                Toast.makeText(DetalleParaEntregar.this,desLatitud , Toast.LENGTH_SHORT).show();
//                Toast.makeText(DetalleParaEntregar.this,desLongitud , Toast.LENGTH_SHORT).show();
//                Toast.makeText(DetalleParaEntregar.this,desCelular , Toast.LENGTH_SHORT).show();
//                Toast.makeText(DetalleParaEntregar.this,desTotalpagar , Toast.LENGTH_SHORT).show();

            }
        });
//         Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("hola"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}