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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservasUsuAdapter extends RecyclerView.Adapter<ReservasUsuAdapter.ReservaUsuViewHolder> implements View.OnClickListener {

    ArrayList<ReservaUsu> datos;
    View.OnClickListener listener;

    public ReservasUsuAdapter(ArrayList<ReservaUsu> datos) {
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

        TextView numPersonas;
        TextView fecha;
        TextView hora;
        TextView restaurante;


        public ReservaUsuViewHolder(@NonNull View itemView) {
            super(itemView);
            numPersonas = itemView.findViewById(R.id.tvNumPersonasReservasItem);
            hora = itemView.findViewById(R.id.tvHoraReservasItem);
            fecha =itemView.findViewById(R.id.tvFechaReservasItem);
            restaurante= itemView.findViewById(R.id.tvNombreResReservasItem);

        }

        public void bindItem(ReservaUsu reservaUsu) {
            numPersonas.setText(String.valueOf(reservaUsu.getNumPersonas()));
            hora.setText(reservaUsu.getHora());
            restaurante.setText(reservaUsu.getNombreRest());


            //Cambiar el formato a DD Mes AAAA
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat dateInput = new SimpleDateFormat("MM-dd-yyyy");

            Date inputDate = null;
            try {
                inputDate = dateInput.parse(reservaUsu.getFecha());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String formateado = "FORMAT ERROR";

            if (inputDate != null) {
                formateado = dateFormat.format(inputDate);
            }
            fecha.setText(formateado);


        }


    }

}
