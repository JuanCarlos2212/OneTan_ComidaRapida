package com.onetan.carlosevalparcial.Comensal;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
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
import com.onetan.carlosevalparcial.databinding.ActivityUbicaciondelComensalBinding;

public class UbicaciondelComensal extends FragmentActivity implements OnMapReadyCallback {

    String recibiendoidPlatillo, recibeprecio;
    Button btnContinuar;

    private GoogleMap mMap;
    private ActivityUbicaciondelComensalBinding binding;
    Double mLat, mLong;
    TextView txtUbicacionComensal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        

        
        binding = ActivityUbicaciondelComensalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        recibiendoidPlatillo = getIntent().getStringExtra("keyidplatillo");
        recibeprecio = getIntent().getStringExtra("keyprecio");

        txtUbicacionComensal = findViewById(R.id.txtUbicacionComensal);
        btnContinuar= findViewById(R.id.btnContinuarUbicacion);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarquienrecibe();

            }
        });
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng unLugar = new LatLng(-12.068488401588287, -75.21016673331222);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Maqui estamos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unLugar));
        CameraPosition cameraPosition = CameraPosition.builder().target(unLugar).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentationA32
            // for ActivityCompat#requestPermissions for more details.
            //mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

        }
        else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "Dio permiso poara utilizar su ubicacion", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marcadorpaquete)).title("Punto").snippet("Mi punto de entrega").position(latLng).anchor(0.5f,0.5f));
                mLat =latLng.latitude;
                mLong=latLng.longitude;
                Toast.makeText(UbicaciondelComensal.this,  "Punto Registrado", Toast.LENGTH_SHORT).show();


//                intent.putExtra("latitud",mLat);
//                intent.putExtra("longitud",mLong);
////                startActivity(intent);
                String latituddecomensal= String.valueOf(mLat);
                String longituddecomensal= String.valueOf(mLong);
                txtUbicacionComensal.setText(latituddecomensal + longituddecomensal);
            }
        });
    }
    private void confirmarquienrecibe() {

        final Dialog guardarquienrecibe = new Dialog(this);
        guardarquienrecibe.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        guardarquienrecibe.setTitle("¿Quien recibira el Pedido?");
        guardarquienrecibe.setContentView(R.layout.dialog_quien_recibir_pedido);
        Button btnSiguiente = (Button) guardarquienrecibe.findViewById(R.id.btnSaveFile);
        RadioButton radioreciboyo = (RadioButton) guardarquienrecibe.findViewById(R.id.radioreciboyo);
        RadioButton recibeotrapersona = (RadioButton) guardarquienrecibe.findViewById(R.id.radioRecibeotrapersona);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioreciboyo.isChecked()==true){

                    Intent intent = new Intent(guardarquienrecibe.getContext(),DetalleParaGuardarPedido.class);

                    intent.putExtra("keyprecio",recibeprecio);
                    intent.putExtra("keyidplatillo",recibiendoidPlatillo);
                    intent.putExtra("mLat",mLat);
                    intent.putExtra("mLong",mLong);
//                    Toast.makeText(UbicaciondelComensal.this, ""+mLong, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(UbicaciondelComensal.this, ""+mLat, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(UbicaciondelComensal.this, ""+recibeprecio, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(UbicaciondelComensal.this, ""+recibiendoidPlatillo, Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }else{
                    Toast.makeText(guardarquienrecibe.getContext(), "Falta Implementar esta seccion", Toast.LENGTH_SHORT).show();
                }


            }
        });
        guardarquienrecibe.show();
    }

}