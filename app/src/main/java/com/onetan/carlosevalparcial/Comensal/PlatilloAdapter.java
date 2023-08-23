package com.onetan.carlosevalparcial.Comensal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onetan.carlosevalparcial.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlatilloAdapter  extends RecyclerView.Adapter<PlatilloAdapter.PlatilloHolder> {

    List<Platillo> listaPlatillo;

    public PlatilloAdapter(ArrayList<Platillo> listaPlatillo) {
        this.listaPlatillo = listaPlatillo;
    }

    @NonNull
    @NotNull
    @Override
    public PlatilloHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comida,parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        vista.setLayoutParams(formaLayout);

        return new PlatilloHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlatilloHolder holder, int position) {


        holder.txtnombre.setText(listaPlatillo.get(position).getNombre());
        holder.txtprecio.setText(listaPlatillo.get(position).getPrecio().toString());
        holder.txtidplato = listaPlatillo.get(position).getIdplatillo().toString();
        holder.descripcion = listaPlatillo.get(position).getDescripcion();
        holder.ruta = listaPlatillo.get(position).getRuta();
        if(listaPlatillo.get(position).getImagen() != null)
            holder.imgPlatillo.setImageBitmap(listaPlatillo.get(position).getImagen());

        else
            holder.imgPlatillo.setImageResource(R.drawable.img_base);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle enviardatos = new Bundle();
                enviardatos.putString("keynombres", String.valueOf(holder.txtnombre.getText()));
                enviardatos.putString("Keydescripcion", String.valueOf(holder.descripcion));
                enviardatos.putString("keyprecio", String.valueOf(holder.txtprecio.getText()));
                enviardatos.putString("keyidplatillo", String.valueOf(holder.txtidplato));
                enviardatos.putString("keyimagen", String.valueOf(holder.ruta));

                Intent intent = new Intent(view.getContext(),DetalledeComida.class);
                intent.putExtras(enviardatos);
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), String.valueOf(holder.txtidplato), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.descripcion), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.txtprecio.getText()), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaPlatillo.size();
    }

    public class PlatilloHolder extends RecyclerView.ViewHolder {

        String txtidplato;
        TextView txtdescipcion;
        TextView txtprecio;
        TextView txtnombre;
        ImageView imgPlatillo;
        String descripcion, ruta;
        public PlatilloHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtnombre = itemView.findViewById(R.id.txtnombreComidaItem);
            imgPlatillo = itemView.findViewById(R.id.imgImagendeComidaItem);
            txtprecio = itemView.findViewById(R.id.txtPrecioItem);
        }
    }
}
