package com.dam.safebar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.safebar.R;
import com.dam.safebar.adapters.InicioAdapter;
import com.dam.safebar.javabeans.Datos;
import com.dam.safebar.javabeans.Restaurante;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    RecyclerView rv;
    ArrayList<Restaurante> listaRestaurantes;

    public InicioFragment() {
        // Required empty public constructor
    }

    public InicioFragment newInstance() {
        InicioFragment fragment = new InicioFragment();
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

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        rv = view.findViewById(R.id.rvInicio);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        final Datos datos = new Datos();
        InicioAdapter inicAdap = new InicioAdapter((datos.getListaRestaurantes()));
//        inicAdap.setListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i = rv.getChildAdapterPosition(v);
//                Restaurante restaurante = datos.getListaRestaurantes().get(i);
//                listener.accederVecinoInicio(usuario);
//            }
//        });

        rv.setAdapter(inicAdap);
        return view;
    }
}