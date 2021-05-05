package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.dam.safebar.listeners.InicioListener;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    RecyclerView rv;
    ArrayList<Restaurante> listaRestaurantes;
    InicioListener listener;

    public InicioFragment() {
        // Required empty public constructor
    }

    public InicioFragment newInstance(ArrayList<Restaurante> listaRestaurantes) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("LISTA_REST", listaRestaurantes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listaRestaurantes = getArguments().getParcelableArrayList("LISTA_REST");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        rv = view.findViewById(R.id.rvInicio);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        InicioAdapter inicAdap = new InicioAdapter(listaRestaurantes);
        inicAdap.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = rv.getChildAdapterPosition(v);
                Restaurante restaurante = listaRestaurantes.get(i);
                listener.abrirRestaurante(restaurante);
            }
        });

        rv.setAdapter(inicAdap);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof InicioListener) {
            listener = (InicioListener) context;
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