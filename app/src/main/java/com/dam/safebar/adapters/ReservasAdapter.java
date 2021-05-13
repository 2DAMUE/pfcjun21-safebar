package com.dam.safebar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.safebar.R;
import com.dam.safebar.javabeans.ReservaUsu;

import java.util.ArrayList;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservaUsuViewHolder> implements View.OnClickListener {

    ArrayList<ReservaUsu> datos;
    View.OnClickListener listener;

    public ReservasAdapter(ArrayList<ReservaUsu> datos) {
        this.datos = datos;
    }


    @NonNull
    @Override
    public ReservaUsuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservas_usu, parent, false);
        v.setOnClickListener(this);
        ReservaUsuViewHolder rvh = new ReservaUsuViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaUsuViewHolder holder, int position) {
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

    public static class ReservaUsuViewHolder extends RecyclerView.ViewHolder {

        Button reservar;
        TextView aforo;
        TextView fecha;
        TextView hora;
        TextView restaurante;


        public ReservaUsuViewHolder(@NonNull View itemView) {
            super(itemView);
            reservar = itemView.findViewById(R.id.btnReservarReservasItem);
            aforo = itemView.findViewById(R.id.tvNumPersonasReservasItem);
            hora = itemView.findViewById(R.id.tvHoraReservasItem);
            fecha =itemView.findViewById(R.id.tvFechaReservasItem);
            restaurante= itemView.findViewById(R.id.tvNombreResReservasItem);

        }

        public void bindItem(ReservaUsu reservaUsu) {
            aforo.setText(String.valueOf(reservaUsu.getNumPersonas()));
            hora.setText(reservaUsu.getHora());
            fecha.setText(reservaUsu.getFecha());
            restaurante.setText(reservaUsu.getNombreRest());

        }


    }

}
