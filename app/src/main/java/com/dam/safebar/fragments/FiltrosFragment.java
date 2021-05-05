package com.dam.safebar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.safebar.R;


public class FiltrosFragment extends Fragment {

    public FiltrosFragment() {
        // Required empty public constructor
    }


    public static FiltrosFragment newInstance() {
        FiltrosFragment fragment = new FiltrosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtros, container, false);
        EditText etFil = view.findViewById(R.id.etEditFiltFragFil);
        Button btnFiltrar = view.findViewById(R.id.btnFiltrarFiltrosFrag);

        /*btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(getContext(), "Filtrado Exitoso!", Toast.LENGTH_SHORT).show();
                listener

            }
        });*/
        return view;
    }
}