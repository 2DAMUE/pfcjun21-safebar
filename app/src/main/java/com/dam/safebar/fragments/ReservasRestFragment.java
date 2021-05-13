package com.dam.safebar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.dam.safebar.R;
import com.dam.safebar.adapters.ReservasAdapter;
import com.dam.safebar.adapters.ReservasRestAdapter;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;


import java.util.ArrayList;


public class ReservasRestFragment extends Fragment {
    RecyclerView rv;
    ArrayList<ReservaRest> listareservas;
    TextView tvFecha;
    TextView tvHora;
    TextView tvNumPer;
    Button btnReservas;

    public ReservasRestFragment() {
        // Required empty public constructor
    }


    public static ReservasRestFragment newInstance(ArrayList<ReservaRest> listareservas) {
        ReservasRestFragment fragment = new ReservasRestFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("LISTA_RESV REST", listareservas);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listareservas = getArguments().getParcelableArrayList("LISTA_RESV REST");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reservas_rest, container, false);
        rv = view.findViewById(R.id.rvReservasRest);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
       ReservasRestAdapter reserRestAdap = new ReservasRestAdapter(listareservas);
        //     reserAdap.setListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i = rv.getChildAdapterPosition(v);
//                ReservaUsu reservausu = Reservas.get(i);
//                listener.accederVecinoInicio(usuario);
//            }
//        });

       rv.setAdapter(reserRestAdap);
        return view;
    }


}