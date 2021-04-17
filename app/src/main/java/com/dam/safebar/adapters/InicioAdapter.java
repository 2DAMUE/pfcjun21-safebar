package com.dam.safebar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.dam.safebar.R;
import com.dam.safebar.javabeans.Restaurante;

import java.util.ArrayList;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.RestauranteViewHolder> implements View.OnClickListener {

    ArrayList<Restaurante> datos;
    View.OnClickListener listener;

    public InicioAdapter(ArrayList<Restaurante> datos) {
        this.datos = datos;
    }


    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rest_inicio, parent, false);
        v.setOnClickListener(this);
        RestauranteViewHolder rvh = new RestauranteViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder holder, int position) {
        holder.bindItem(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public static class RestauranteViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView distancia;
        TextView nombre;
        TextView direccion;
        TextView aforo;
        TextView calif;
        TextView precio;

        public RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgItemRVInicio);
            distancia = itemView.findViewById(R.id.tvKmItemRVInicio);
            nombre = itemView.findViewById(R.id.tvNomItemRVInicio);
            direccion = itemView.findViewById(R.id.tvDirecItemRVInicio);
            aforo = itemView.findViewById(R.id.tvAforoItemRVInicio);
            calif = itemView.findViewById(R.id.tvCalifItemRVInicio);
            precio = itemView.findViewById(R.id.tvPrecioItemRVInicio);

        }

        public void bindItem(Restaurante restaurante) {
            imagen.setImageResource(Integer.parseInt(restaurante.getUrlFoto()));

            //TODO:

            Glide.with(imagen)
                    .load(restaurante.getUrlFoto())
                    .placeholder(R.drawable.usuario_1)
                    .circleCrop()
                    .into(imagen);

            distancia.setText("km");
            nombre.setText(restaurante.getNombreRest());
            direccion.setText(restaurante.getDireccion());
            aforo.setText(restaurante.getAforo());
            calif.setText(String.valueOf(restaurante.getCalificacion()));
            precio.setText(restaurante.getPrecioMedio());
        }


    }

}
