package com.onetan.carlosevalparcial.Repartidor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onetan.carlosevalparcial.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.PedidosHolder> {
    List<Pedidos> listPedidos;

    public AdapterPedidos(ArrayList<Pedidos> listaPedidos) {
        this.listPedidos=listaPedidos;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterPedidos.PedidosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedidos,parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        vista.setLayoutParams(formaLayout);

        return new PedidosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PedidosHolder holder, int position) {

        holder.nombre.setText(listPedidos.get(position).getNombre());
        holder.nombresusuario.setText(listPedidos.get(position).getNombres());
        holder.idpedido = String.valueOf(listPedidos.get(position).getIdpedidos());
        holder.latitud = listPedidos.get(position).getLatitud();
        holder.longitud = listPedidos.get(position).getLongitud();
        holder.celular = listPedidos.get(position).getCelular();
        holder.precio = String.valueOf(listPedidos.get(position).getPrecio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle enviardatos = new Bundle();
                enviardatos.putString("nombrecomida", String.valueOf(holder.nombre.getText()));
                enviardatos.putString("nombreusuario", String.valueOf(holder.nombresusuario.getText()));
                enviardatos.putString("idpedidos", String.valueOf(holder.idpedido));
                enviardatos.putString("latitud", String.valueOf(holder.latitud));
                enviardatos.putString("longitud", String.valueOf(holder.longitud));
                enviardatos.putString("celular", String.valueOf(holder.celular));
                enviardatos.putString("precio", String.valueOf(holder.precio));

                Intent intent = new Intent(view.getContext(), DetalleParaEntregar.class);
                intent.putExtras(enviardatos);
                view.getContext().startActivity(intent);

//                Toast.makeText(view.getContext(), String.valueOf(holder.nombre.getText()), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.nombresusuario.getText()), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.idpedido), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.latitud), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.longitud), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.celular), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), String.valueOf(holder.precio), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPedidos.size();
    }

    public class PedidosHolder extends RecyclerView.ViewHolder {

        TextView nombresusuario,nombre;
        String latitud, longitud,celular,precio,idpedido;
        public PedidosHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nombresusuario= itemView.findViewById(R.id.txtnombreusuarioItempedido);
            nombre= itemView.findViewById(R.id.txtcombreComidaItempedido);

        }
    }
}
