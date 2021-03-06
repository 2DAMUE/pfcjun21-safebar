package com.dam.safebar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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
        return new RestauranteViewHolder(v);
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
//        TextView distancia;
        TextView nombre;
        TextView direccion;
        TextView aforo;
        RatingBar rtbInicio;
        TextView precio;

        public RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgItemRVInicio);
//            distancia = itemView.findViewById(R.id.tvKmItemRVInicio);
            nombre = itemView.findViewById(R.id.tvNomItemRVInicio);
            direccion = itemView.findViewById(R.id.tvDirecItemRVInicio);
            aforo = itemView.findViewById(R.id.tvAforoItemRVInicio);
            rtbInicio = itemView.findViewById(R.id.ratingBarInicio);
            precio = itemView.findViewById(R.id.tvPrecioItemRVInicio);

        }

        public void bindItem(Restaurante restaurante) {
            //imagen.setImageResource(Integer.parseInt(restaurante.getUrlFoto()));


            Glide.with(imagen)
                    .load(restaurante.getUrlFoto())
                    .placeholder(null)
                    .into(imagen);

//            distancia.setText("km");
            nombre.setText(restaurante.getNombreRest());
            direccion.setText(restaurante.getDireccion());
            aforo.setText(String.valueOf(restaurante.getAforo()));
            rtbInicio.setRating(restaurante.getCalificacion());
            precio.setText(String.valueOf(restaurante.getPrecioMedio()));
        }


    }

}
