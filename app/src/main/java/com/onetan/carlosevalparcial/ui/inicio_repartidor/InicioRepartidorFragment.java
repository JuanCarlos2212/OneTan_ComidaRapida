package com.onetan.carlosevalparcial.ui.inicio_repartidor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.onetan.carlosevalparcial.R;
import com.onetan.carlosevalparcial.Repartidor.AdapterPedidos;
import com.onetan.carlosevalparcial.Repartidor.Pedidos;
import com.onetan.carlosevalparcial.Repartidor.PedidosEntregadosAdapter;
import com.onetan.carlosevalparcial.Util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class InicioRepartidorFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerPedidos, recyclerPedidosEntregados;
    ArrayList<Pedidos> listaPedidos, listaPedidosEntregados;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    public InicioRepartidorFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_inicio_repartidor,container,false);

        requestQueue = Volley.newRequestQueue(getContext());

        listaPedidos = new ArrayList<>();
        recyclerPedidos = vista.findViewById(R.id.recyPedidoinicio);
        recyclerPedidos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPedidos.setHasFixedSize(true);

        cargarListaPedidos();

        listaPedidosEntregados = new ArrayList<>();
        recyclerPedidosEntregados = vista.findViewById(R.id.recyPedidoEntregago);
        recyclerPedidosEntregados.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerPedidos.setHasFixedSize(true);

        cargarListaPedidosEntregados();
        // Inflate the layout for this fragment
        return vista;
    }

    private void cargarListaPedidos() {

        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Buscando Pedidos en espera");
        progreso.show();
        String url = Util.RUTA + "listarpedidosespera.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        requestQueue.add(jsonObjectRequest);
        progreso.hide();
    }
    private void cargarListaPedidosEntregados() {

        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Buscando Pedidos Entregados");
        progreso.show();
        String url = Util.RUTA + "listarpedidosentregados.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Pedidos pedidos = null;
                progreso.hide();
                JSONArray json = response.optJSONArray("pedidosentregados");

                try {
                    for(int i=0; i<json.length();i++){
                        pedidos = new Pedidos();

                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);

                        pedidos.setNombres(jsonObject.getString("nombres"));
                        pedidos.setNombre(jsonObject.getString("nombre"));

                        pedidos.setLatitud(jsonObject.getString("latitud"));
                        pedidos.setLongitud(jsonObject.getString("longitud"));
                        pedidos.setPrecio(jsonObject.getDouble("precio"));

                        pedidos.setIdpedidos(jsonObject.getInt("idpedidos"));
                        pedidos.setCelular(jsonObject.getString("celular"));
                        pedidos.setIdusuario(jsonObject.getInt("idusuario"));

                        listaPedidosEntregados.add(pedidos);
                    }
                    PedidosEntregadosAdapter adapter = new PedidosEntregadosAdapter(listaPedidosEntregados);
                    recyclerPedidosEntregados.setAdapter(adapter);

                }catch (Exception e){ e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error en Volley",error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Pedidos pedidos = null;
        progreso.hide();
        JSONArray json = response.optJSONArray("pedidosespera");

        try {
            for(int i=0; i<json.length();i++){
                pedidos = new Pedidos();

                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                pedidos.setNombres(jsonObject.getString("nombres"));
                pedidos.setNombre(jsonObject.getString("nombre"));

                pedidos.setLatitud(jsonObject.getString("latitud"));
                pedidos.setLongitud(jsonObject.getString("longitud"));
                pedidos.setPrecio(jsonObject.getDouble("precio"));

                pedidos.setIdpedidos(jsonObject.getInt("idpedidos"));
                pedidos.setCelular(jsonObject.getString("celular"));
                pedidos.setIdusuario(jsonObject.getInt("idusuario"));

                listaPedidos.add(pedidos);
            }
            AdapterPedidos adapter = new AdapterPedidos(listaPedidos);
            recyclerPedidos.setAdapter(adapter);

        }catch (Exception e){ e.printStackTrace();}

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("error en Volley",error.toString());

    }
}