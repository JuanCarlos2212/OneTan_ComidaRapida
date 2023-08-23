package com.onetan.carlosevalparcial.Repartidor;

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

public class PedidosEntregadosAdapter extends RecyclerView.Adapter<PedidosEntregadosAdapter.PedidosEntregadosHolder> {
    List<Pedidos> listPedidosEntregdos;

    public PedidosEntregadosAdapter(ArrayList<Pedidos> listPedidosEntregdos) {
        this.listPedidosEntregdos=listPedidosEntregdos;
    }
    @NonNull
    @NotNull
    @Override
    public PedidosEntregadosHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedidos_entregados,parent,false);
        RecyclerView.LayoutParams formaLayout = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        vista.setLayoutParams(formaLayout);

        return new PedidosEntregadosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PedidosEntregadosHolder holder, int position) {

        holder.nombre.setText(listPedidosEntregdos.get(position).getNombres());
        holder.comida.setText(listPedidosEntregdos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listPedidosEntregdos.size();
    }

    public class PedidosEntregadosHolder extends RecyclerView.ViewHolder {
        TextView nombre, comida;
        public PedidosEntregadosHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.txtnombreusuarioItempedidoEntregado);
            comida= itemView.findViewById(R.id.txtcombreComidaItempedidoEntregado);
        }
    }
}
