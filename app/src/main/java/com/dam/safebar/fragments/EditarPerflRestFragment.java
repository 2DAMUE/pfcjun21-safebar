package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.listeners.CuentaListener;
import com.dam.safebar.listeners.PerfilRestListener;


public class EditarPerflRestFragment extends Fragment {

    PerfilRestListener listener;

    public EditarPerflRestFragment() {
        // Required empty public constructor
    }


    public EditarPerflRestFragment newInstance() {
        EditarPerflRestFragment fragment = new EditarPerflRestFragment();
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

        View view = inflater.inflate(R.layout.fragment_editar_perfl_rest, container, false);


        ImageView imageView = view.findViewById(R.id.imgEditPerfRestFrag);
        ImageButton imbEditarImagen = view.findViewById(R.id.imbEditPerfRestFrag);
        EditText etNombre = view.findViewById(R.id.etEditPerfRestFragNom);
        EditText etEmail = view.findViewById(R.id.etEditPerfRestFragEmail);
        EditText etPW = view.findViewById(R.id.etEditPerfRestFragPW);
        EditText etDirec = view.findViewById(R.id.etEditPerfRestFragDirec);
        EditText etPrecio = view.findViewById(R.id.etEditPerfRestFragPrecio);
        EditText etAforo = view.findViewById(R.id.etEditPerfRestFragAforo);
        EditText etDescrip = view.findViewById(R.id.etEditPerfRestFragDescrip);
        Button btnGuardar = view.findViewById(R.id.btnEditPerfRestFragGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Firebase

                listener.volverPerfilRest();
            }
        });




        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PerfilRestListener) {
            listener = (PerfilRestListener) context;
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