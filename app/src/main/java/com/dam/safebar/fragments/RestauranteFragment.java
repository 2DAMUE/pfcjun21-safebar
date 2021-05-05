package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.PerfilRestListener;
import com.dam.safebar.listeners.ReservarListener;


public class RestauranteFragment extends Fragment {

    public static final String COD_REST = "R1";

    Restaurante restaurante;

    ReservarListener listener;

    ImageView img;
    TextView tvNom;
    TextView tvDirec;
    TextView tvCalif;
    TextView tvPrecio;
    TextView tvAforo;
    TextView tvDescrip;
    Button btnReservar;

    public RestauranteFragment() {
        // Required empty public constructor
    }

    public RestauranteFragment newInstance(Restaurante restaurante) {
        RestauranteFragment fragment = new RestauranteFragment();
        Bundle args = new Bundle();
        args.putParcelable(COD_REST, restaurante);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurante = getArguments().getParcelable(COD_REST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        img = view.findViewById(R.id.imgRestauranteFrag);
        tvNom = view.findViewById(R.id.tvNombreRestauranteFrag);
        tvDirec = view.findViewById(R.id.tvDirecRestauranteFrag);
        tvCalif = view.findViewById(R.id.tvCalifRestauranteFrag);
        tvPrecio = view.findViewById(R.id.tvPrecioRestauranteFrag);
        tvAforo = view.findViewById(R.id.tvAforoRestauranteFrag);
        tvDescrip = view.findViewById(R.id.tvDescripRestauranteFrag);
        btnReservar = view.findViewById(R.id.btnReservarRestauranteFrag);

        Glide.with(img)
                .load(restaurante.getUrlFoto())
                .placeholder(null)
                .into(img);

        tvNom.setText(restaurante.getNombreRest());
        tvDirec.setText(restaurante.getDireccion());
        tvCalif.setText(String.valueOf(restaurante.getCalificacion()));
        tvPrecio.setText(String.valueOf(restaurante.getPrecioMedio()));
        tvAforo.setText(String.valueOf(restaurante.getAforo()));
        tvDescrip.setText(restaurante.getDescripcion());

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.reservar();

                //TODO: Firebase

            }
        });

        return view;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReservarListener) {
            listener = (ReservarListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}