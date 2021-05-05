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
import com.dam.safebar.javabeans.ReservaUsu;

import java.util.ArrayList;


public class ReservasUsuFragment extends Fragment {
    RecyclerView rv;
    ArrayList<ReservaUsu> listareservas;
    TextView tvFecha;
    TextView tvHora;
    TextView tvNumPer;
    Button btnReservas;



    public ReservasUsuFragment() {
        // Required empty public constructor
    }


    public ReservasUsuFragment newInstance(ArrayList<ReservaUsu> listareservas) {
        ReservasUsuFragment fragment = new ReservasUsuFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("LISTA_RESV", listareservas);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listareservas = getArguments().getParcelableArrayList("LISTA_RESV");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas_usu, container, false);
        /*tvFecha = view.findViewById(R.id.tvFechaReservasFrag);
        tvHora = view.findViewById(R.id.tvHoraReservasFrag);
        tvNumPer = view.findViewById(R.id.tvNumPersonasReservasFrag);
        btnReservas = view.findViewById(R.id.btnReservarReservasFrag);

         */
        rv = view.findViewById(R.id.rvReservas);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        ReservasAdapter reserAdap = new ReservasAdapter(listareservas);
  //     reserAdap.setListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i = rv.getChildAdapterPosition(v);
//                ReservaUsu reservausu = Reservas.get(i);
//                listener.accederVecinoInicio(usuario);
//            }
//        });

        rv.setAdapter(reserAdap);
        return view;


    }



}