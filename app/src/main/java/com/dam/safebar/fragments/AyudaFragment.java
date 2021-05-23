package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CuentaListener;
import com.dam.safebar.listeners.InicioListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AyudaFragment extends Fragment {

    CuentaListener listener;
    FirebaseAuth fba;
    FirebaseUser user;

    Button btnLLamar;
    Button btnEmail;

    public AyudaFragment() {
        // Required empty public constructor
    }

    public AyudaFragment newInstance() {
        AyudaFragment fragment = new AyudaFragment();
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
        View view = inflater.inflate(R.layout.fragment_ayuda, container, false);

        btnLLamar =view.findViewById(R.id.btnLlamarAyudaFrag);
        btnEmail =view.findViewById(R.id.btnEmailAyudaFrag);

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        btnLLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.llamar();
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.mandarCorreo(user.getEmail());
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