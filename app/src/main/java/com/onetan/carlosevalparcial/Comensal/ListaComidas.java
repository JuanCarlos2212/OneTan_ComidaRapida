package com.onetan.carlosevalparcial.Comensal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.R;
import com.onetan.carlosevalparcial.Util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaComidas extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerPlatillos;

    ArrayList<Platillo> listaPlatillo;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    TextView txtNombreCategoriaComida;
    String categoriAListar;
    public ListaComidas() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comidas);
        getSupportActionBar().hide();

        txtNombreCategoriaComida= findViewById(R.id.txtNombreCategoriaComida);

        listaPlatillo = new ArrayList<>();
        recyclerPlatillos = findViewById(R.id.recyComidas);
        recyclerPlatillos.setLayoutManager(new LinearLayoutManager(this));
        recyclerPlatillos.setHasFixedSize(true);

        requestQueue = Volley.newRequestQueue(this);

        String nombredecategoria = getIntent().getStringExtra("nombrecategoria");
        if(nombredecategoria.equals("Sopas")){
            categoriAListar = "1";
        }else if(nombredecategoria.equals("Tallarines")){
            categoriAListar = "2";
        }else if(nombredecategoria.equals("Arroz")){
            categoriAListar = "3";
        }else categoriAListar = "4";
        txtNombreCategoriaComida.setText(nombredecategoria);

        cargarListadePlatillos();
    }

    private void cargarListadePlatillos() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Buscando Platillos");
        progreso.show();
        String url = Util.RUTA + "consultarcomida.php?idcategoria="+categoriAListar ;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        Platillo platillo = null;
        progreso.hide();
        JSONArray json = response.optJSONArray("platillo");

        try {
            for(int i=0; i<json.length();i++){
                platillo = new Platillo();

                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                platillo.setIdplatillo(jsonObject.getInt("idplatillo"));
                platillo.setNombre(jsonObject.getString("nombre"));
                platillo.setDescripcion(jsonObject.getString("descripcion"));
                platillo.setPrecio(jsonObject.getDouble("precio"));
                platillo.setDataImagen(jsonObject.getString("imagen"));

                platillo.setRuta(jsonObject.getString("imagen"));


                listaPlatillo.add(platillo);
            }
            PlatilloAdapter adapter = new PlatilloAdapter(listaPlatillo);
            recyclerPlatillos.setAdapter(adapter);

        }catch (Exception e){ e.printStackTrace();}
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("error en Volley",error.toString());
    }
}