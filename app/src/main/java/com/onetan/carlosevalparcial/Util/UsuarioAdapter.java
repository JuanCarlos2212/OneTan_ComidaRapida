package com.onetan.carlosevalparcial.Util;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onetan.carlosevalparcial.Usuario;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder> {
    List<Usuario> listaUsuario;

    @NonNull
    @NotNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UsuarioHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {
        String usucontra, usucorreo;
        public UsuarioHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
