package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CheckQRListener;
import com.dam.safebar.listeners.QRListener;


public class QRFragment extends Fragment {

    QRListener listener;

    String codigoReserva;

    public QRFragment() {
        // Required empty public constructor
    }


    public QRFragment newInstance(String codigoReserva) {
        QRFragment fragment = new QRFragment();
        Bundle args = new Bundle();
        args.putString("CODIGO_RESERVA", codigoReserva);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            codigoReserva = getArguments().getString("CODIGO_RESERVA");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qr, container, false);


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof QRListener) {
            listener = (QRListener) context;
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