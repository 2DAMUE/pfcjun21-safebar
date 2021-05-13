package com.dam.safebar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.safebar.R;
import com.dam.safebar.javabeans.ReservaRest;


import java.util.ArrayList;

public class ReservasRestAdapter extends RecyclerView.Adapter<ReservasRestAdapter.ReservaRestViewHolder> implements View.OnClickListener {

    ArrayList<ReservaRest> datos;
    View.OnClickListener listener;

    public ReservasRestAdapter(ArrayList<ReservaRest> datos) {
        this.datos = datos;
    }


    @NonNull
    @Override
    public ReservaRestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservas_rest, parent, false);
        v.setOnClickListener(this);
        ReservaRestViewHolder rvh = new ReservaRestViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaRestViewHolder holder, int position) {
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

    public static class ReservaRestViewHolder extends RecyclerView.ViewHolder {

        TextView aforo;
        TextView fecha;
        TextView hora;
        TextView usuario;


        public ReservaRestViewHolder(@NonNull View itemView) {
            super(itemView);
            aforo = itemView.findViewById(R.id.tvAforoReservasRestItem);
            hora = itemView.findViewById(R.id.tvHoraReservasRestItem);
            fecha =itemView.findViewById(R.id.tvFechaReservasRestItem);
            usuario =itemView.findViewById(R.id.tvIDReservasRestItem);

        }

        public void bindItem(ReservaRest reservaRest) {
            aforo.setText(String.valueOf(reservaRest.getNumPersonas()));
            hora.setText(reservaRest.getHora());
            fecha.setText(reservaRest.getFecha());
            usuario.setText(reservaRest.getUserUID());

        }


    }

}

