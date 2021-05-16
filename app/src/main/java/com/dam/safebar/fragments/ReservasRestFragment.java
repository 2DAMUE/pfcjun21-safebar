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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.safebar.R;
import com.dam.safebar.adapters.ReservasRestAdapter;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.listeners.ReservasRestListener;
import com.dam.safebar.listeners.ReservasUsuListener;


import java.util.ArrayList;


public class ReservasRestFragment extends Fragment {
    RecyclerView rv;
    ArrayList<ReservaRest> listaReservas;

    ReservasRestListener listener;

    public ReservasRestFragment() {
        // Required empty public constructor
    }


    public ReservasRestFragment newInstance(ArrayList<ReservaRest> listaReservas) {
        ReservasRestFragment fragment = new ReservasRestFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("LISTA_RESV_REST", listaReservas);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listaReservas = getArguments().getParcelableArrayList("LISTA_RESV_REST");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reservas_rest, container, false);
        rv = view.findViewById(R.id.rvReservasRest);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ReservasRestAdapter reserRestAdap = new ReservasRestAdapter(listaReservas);
        reserRestAdap.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = rv.getChildAdapterPosition(v);
                ReservaRest reservaRest = listaReservas.get(i);
                listener.abrirFragmentCheckQR();
            }
        });

       rv.setAdapter(reserRestAdap);
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReservasRestListener) {
            listener = (ReservasRestListener) context;
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