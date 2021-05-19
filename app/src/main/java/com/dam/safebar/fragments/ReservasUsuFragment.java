package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.safebar.R;
import com.dam.safebar.adapters.ReservasUsuAdapter;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.listeners.InicioListener;
import com.dam.safebar.listeners.QRListener;
import com.dam.safebar.listeners.ReservasUsuListener;

import java.util.ArrayList;


public class ReservasUsuFragment extends Fragment {

    RecyclerView rv;
    ArrayList<ReservaUsu> listaReservas;

    ReservasUsuListener listener;

    public ReservasUsuFragment() {
        // Required empty public constructor
    }


    public ReservasUsuFragment newInstance(ArrayList<ReservaUsu> listaReservas) {
        ReservasUsuFragment fragment = new ReservasUsuFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("LISTA_RESV", listaReservas);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listaReservas = getArguments().getParcelableArrayList("LISTA_RESV");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas_usu, container, false);

        rv = view.findViewById(R.id.rvReservas);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ReservasUsuAdapter reserAdap = new ReservasUsuAdapter(listaReservas);
       reserAdap.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = rv.getChildAdapterPosition(v);
                ReservaUsu reservaUsu = listaReservas.get(i);
                listener.abrirFragmentQR(reservaUsu.getCodigo());
            }
        });

        rv.setAdapter(reserAdap);
        return view;


    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReservasUsuListener) {
            listener = (ReservasUsuListener) context;
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