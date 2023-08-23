package com.onetan.carlosevalparcial.Repartidor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import com.onetan.carlosevalparcial.databinding.ActivityMejorRutaBinding;

public class MejorRuta extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private ActivityMejorRutaBinding binding;

    String mejorNombre,mejorNombreplato,mejorCelular,mejorLongitud,mejorLatitud,mejorTotalpagar,mejoridpedido;
    Button entregarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMejorRutaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        entregarPedido = findViewById(R.id.btnContinuarUbicacion);


        mejorNombre = getIntent().getStringExtra("nombreusuario");
        mejorNombreplato = getIntent().getStringExtra("nombrecomida");
        mejorCelular = getIntent().getStringExtra("celular");
        mejorLongitud = getIntent().getStringExtra("longitud");
        mejorLatitud = getIntent().getStringExtra("latitud");
        mejorTotalpagar = getIntent().getStringExtra("precio");
        mejoridpedido = getIntent().getStringExtra("idpedidos");

        Double latitud = Double.valueOf(mejorLatitud);
        Double longitud = Double.valueOf(mejorLongitud);


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("mejor ruta"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng unLugar = new LatLng(longitud,latitud);

        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marcadorpaquete)).position(unLugar).title("Para: "+mejorNombre).snippet(" Celular a llamar: "+mejorCelular).anchor(0.5f,0.5f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unLugar));
        CameraPosition cameraPosition = CameraPosition.builder().target(unLugar).zoom(17).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        entregarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MejorRuta.this,TomarFotografia.class);
                intent.putExtra("idpedidos",mejoridpedido);
                startActivity(intent);
            }
        });
    }
}