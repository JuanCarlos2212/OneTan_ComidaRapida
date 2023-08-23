package com.onetan.carlosevalparcial.ui.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.onetan.carlosevalparcial.Comensal.ListaComidas;
import com.onetan.carlosevalparcial.R;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    SearchView searchView;
    ImageSlider imagenslider;
    CardView sopas,tallarin,arroz,bebidas;
    String catsopa,cattallarin,catarroz,catbebidas;
    TextView txtcatsopa,txtcattallarin,txtcatarroz,txtcatbebidas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.fragment_inicio,container,false);
//
        imagenslider= vista.findViewById(R.id.slidercomidaspopulares);
        sopas= vista.findViewById(R.id.cardSopas);
        tallarin= vista.findViewById(R.id.cardTallarines);
        arroz= vista.findViewById(R.id.cardArroz);
        bebidas= vista.findViewById(R.id.cardBebidas);
        searchView= vista.findViewById(R.id.buscarComida);

        //nombres de categorias
        txtcatsopa= vista.findViewById(R.id.txtSopaInicio);
        txtcattallarin= vista.findViewById(R.id.txttallarinInicio);
        txtcatarroz= vista.findViewById(R.id.txtArrozinicio);
        txtcatbebidas= vista.findViewById(R.id.bebidasinicio);

        catsopa = txtcatsopa.getText().toString();
        cattallarin = txtcattallarin.getText().toString();
        catarroz = txtcatarroz.getText().toString();
        catbebidas = txtcatbebidas.getText().toString();

        List<SlideModel> slideModels = new ArrayList<>();
//todo del slider
        slideModels.add(new SlideModel(R.drawable.poollo_5sabores,"Pollo 5 Sabores"));
        slideModels.add(new SlideModel(R.drawable.arrozchaufa_chanchoasado,"Arroz Chaufa con Carne de Chancho"));
        slideModels.add(new SlideModel(R.drawable.tallarin_saltado_lomofino,"Tallarin Saltado con Lomo Fino"));
        slideModels.add(new SlideModel(R.drawable.frejolito, "Arroz Chaufa con Frejolito Chino"));
        imagenslider.setImageList(slideModels,true);


        sopas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListaComidas.class);
                intent.putExtra("nombrecategoria",catsopa);
                startActivity(intent);

            }
        });
        tallarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListaComidas.class);
                intent.putExtra("nombrecategoria",cattallarin);
                startActivity(intent);
            }
        });
        arroz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListaComidas.class);
                intent.putExtra("nombrecategoria",catarroz);
                startActivity(intent);
            }
        });
        bebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListaComidas.class);
                intent.putExtra("nombrecategoria",catbebidas);
                startActivity(intent);
            }
        });


        return vista;
    }
}