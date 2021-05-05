package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CuentaListener;

public class CuentaFragment extends Fragment {

    CuentaListener listener;

    public CuentaFragment() {
        // Required empty public constructor
    }

    public CuentaFragment newInstance() {
        CuentaFragment fragment = new CuentaFragment();
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
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);
        Button btnConfiguracion = view.findViewById(R.id.btnCuentaConfiguracion);
        Button btnAboutUs = view.findViewById(R.id.btnCuentaAboutUs);
        Button btnAyuda = view.findViewById(R.id.btnCuentaAyuda);
        Button btnProtocoloCovid = view.findViewById(R.id.btnCuentaProtocoloCovid);
        TextView tvEmail = view.findViewById(R.id.tvCuentaEmail);

        //TODO: METER EL EMAIL (USUARIO) DE LA CUENTA ACTUAL
        tvEmail.setText("email_cuenta_actual@email.com");

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirConfiguracion();
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirAboutUs();
            }
        });

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirAyuda();
            }
        });

        btnProtocoloCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirProtocoloCovid();
            }
        });

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CuentaListener) {
            listener = (CuentaListener) context;
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