package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CuentaListener;
import com.dam.safebar.listeners.PerfilRestListener;


public class PerfilRestFragment extends Fragment {

    PerfilRestListener listener;

    public PerfilRestFragment() {
        // Required empty public constructor
    }


    public PerfilRestFragment newInstance() {
        PerfilRestFragment fragment = new PerfilRestFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil_rest, container, false);

        ImageView img = view.findViewById(R.id.imgPerfRestFrag);
        TextView tvNom = view.findViewById(R.id.tvNombrePerfRestFrag);
        TextView tvEmail = view.findViewById(R.id.tvEmailPerfRestFrag);
        TextView tvDirec = view.findViewById(R.id.tvDirecPerfRestFrag);
        TextView tvTelef = view.findViewById(R.id.tvTelefPerfRestFrag);
        TextView tvPrecio = view.findViewById(R.id.tvPrecioPerfRestFrag);
        TextView tvAforo = view.findViewById(R.id.tvAforoPerfRestFrag);
        TextView tvDescrip = view.findViewById(R.id.tvDescripPerfRestFrag);
        Button btnEC = view.findViewById(R.id.btnEditCuentaPerfRestFrag);
        Button btnLO = view.findViewById(R.id.btnLogOutPerfRestFrag);

        btnEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.abrirEditCuenta();

            }
        });

        btnLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: hacer LogOut
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PerfilRestListener) {
            listener = (PerfilRestListener) context;
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