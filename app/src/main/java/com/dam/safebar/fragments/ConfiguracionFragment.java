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

import com.dam.safebar.Cuenta;
import com.dam.safebar.R;
import com.dam.safebar.listeners.CuentaListener;

public class ConfiguracionFragment extends Fragment {

    CuentaListener listener;

    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    public ConfiguracionFragment newInstance() {
        ConfiguracionFragment fragment = new ConfiguracionFragment();
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
        View view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        TextView tvNom = view.findViewById(R.id.tvNombreConfigFrag);
        TextView tvEmail = view.findViewById(R.id.tvEmailConfigFrag);
        TextView tvDirec = view.findViewById(R.id.tvDirecConfigFrag);
        Button btnEP = view.findViewById(R.id.btnEditPerfConfigFrag);
        Button btnLO = view.findViewById(R.id.btnLogOutConfigFrag);

        btnEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirEditPerf();
            }
        });

        //TODO: Hacer el Logout

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