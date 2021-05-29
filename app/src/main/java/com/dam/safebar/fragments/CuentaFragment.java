package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dam.safebar.R;
import com.dam.safebar.javabeans.Usuario;
import com.dam.safebar.listeners.CuentaListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CuentaFragment extends Fragment {

    CuentaListener listener;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    ValueEventListener vel;

    Usuario usuario;

    TextView tvEmail;

    public CuentaFragment() {
        // Required empty public constructor
    }

    public CuentaFragment newInstance() {
        CuentaFragment fragment = new CuentaFragment();
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
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);
        Button btnConfiguracion = view.findViewById(R.id.btnCuentaConfiguracion);
        Button btnAboutUs = view.findViewById(R.id.btnCuentaAboutUs);
        Button btnAyuda = view.findViewById(R.id.btnCuentaAyuda);
        Button btnProtocoloCovid = view.findViewById(R.id.btnCuentaProtocoloCovid);
        tvEmail = view.findViewById(R.id.tvCuentaEmail);

        dbRef = FirebaseDatabase.getInstance().getReference("datos/usuarios");
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        addListener();

        btnConfiguracion.setOnClickListener(v -> listener.abrirConfiguracion());

        btnAboutUs.setOnClickListener(v -> listener.abrirAboutUs());

        btnAyuda.setOnClickListener(v -> listener.abrirAyuda());

        btnProtocoloCovid.setOnClickListener(v -> listener.abrirProtocoloCovid());

        return view;

    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        addListener();
//    }

    private void addListener() {
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    usuario = dataSnapshot.getValue(Usuario.class);

                    removeListener();

                    tvEmail.setText(usuario.getEmail());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.bottomNavigationBar);
                    snackbar.show();
                }
            };
            dbRef.child(user.getUid()).addValueEventListener(vel);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        removeListener();
    }

    private void removeListener() {
        if (vel != null) {
            dbRef.removeEventListener(vel);
            vel = null;
        }

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