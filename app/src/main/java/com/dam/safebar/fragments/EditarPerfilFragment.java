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
import android.widget.TextView;
import android.widget.Toast;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CuentaListener;


public class EditarPerfilFragment extends Fragment {

    CuentaListener listener;

    public EditarPerfilFragment() {
        // Required empty public constructor
    }


    public EditarPerfilFragment newInstance() {
        EditarPerfilFragment fragment = new EditarPerfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        ImageView imageView = view.findViewById(R.id.imgEditPerfFrag);
        ImageButton imbEditarImagen = view.findViewById(R.id.imbEditPerfFrag);
        EditText etNom = view.findViewById(R.id.etEditPerfFragNom);
        EditText etEmail = view.findViewById(R.id.etEditPerfFragEmail);
        EditText etPw = view.findViewById(R.id.etEditPerfFragPW);
        EditText etDirec = view.findViewById(R.id.etEditPerfFragDirec);
        Button btnGuardar = view.findViewById(R.id.btnGuardarEditPerfFrag);
        Button btnCancelar = view.findViewById(R.id.btnCancelarEditPerfFrag);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Firebasee

                Toast.makeText(getContext(), "Cambios Guardados!", Toast.LENGTH_SHORT).show();
                listener.abrirConfiguracion();

            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirConfiguracion();
                Toast.makeText(getContext(), "cambios cancelados", Toast.LENGTH_SHORT).show();

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