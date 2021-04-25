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

import com.dam.safebar.R;
import com.dam.safebar.listeners.PerfilRestListener;
import com.dam.safebar.listeners.ReservarListener;


public class RestauranteFragment extends Fragment {

    ReservarListener listener;

    public RestauranteFragment() {
        // Required empty public constructor
    }

    public RestauranteFragment newInstance() {
        RestauranteFragment fragment = new RestauranteFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        ImageView img = view.findViewById(R.id.imgRestauranteFrag);
        TextView tvNom = view.findViewById(R.id.tvNombreRestauranteFrag);
        TextView tvDirec = view.findViewById(R.id.tvDirecRestauranteFrag);
        TextView tvCalif = view.findViewById(R.id.tvCalifRestauranteFrag);
        TextView tvPrecio = view.findViewById(R.id.tvPrecioRestauranteFrag);
        TextView tvAforo = view.findViewById(R.id.tvAforoRestauranteFrag);
        TextView tvDescrip = view.findViewById(R.id.tvDescripRestauranteFrag);
        Button btnReservar = view.findViewById(R.id.btnReservarRestauranteFrag);

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