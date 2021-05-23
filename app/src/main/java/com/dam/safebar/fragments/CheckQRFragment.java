package com.dam.safebar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.ReservasRest;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.CheckQRListener;
import com.dam.safebar.listeners.InicioListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class CheckQRFragment extends Fragment {

    CheckQRListener listener;
    Button btnEscanear;
    TextView tvCodigo;
    ImageView imResult;
    Button btnValidar;

    ReservaRest reservaRest;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    ValueEventListener vel;

    String codigdoReservaUsu;

    public CheckQRFragment() {
        // Required empty public constructor
    }


    public CheckQRFragment newInstance(ReservaRest reservaRest) {
        CheckQRFragment fragment = new CheckQRFragment();
        Bundle args = new Bundle();
        args.putParcelable("RESERV_REST", reservaRest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            reservaRest = getArguments().getParcelable("RESERV_REST");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                btnEscanear.setVisibility(View.GONE);
                imResult.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_check_100, null));
                String resultConcat = getResources().getString(R.string.reserva_verificada) + "\n ID: " + result.getContents();
                tvCodigo.setText(resultConcat);
                tvCodigo.setTextColor(getResources().getColor(R.color.green_light));
                codigdoReservaUsu = String.valueOf(result.getContents());
                btnValidar.setEnabled(true);

            } else {
                tvCodigo.setText(getResources().getString(R.string.error_lectura_qr));
                tvCodigo.setTextColor(getResources().getColor(R.color.orange_dark));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_check_qr, container, false);
        imResult = view.findViewById(R.id.imgQRStatus);
        tvCodigo = view.findViewById(R.id.tvCodigo);

        getActionBar();

        btnEscanear = view.findViewById(R.id.btnEscanear);
        btnValidar= view.findViewById(R.id.btnValidar);

        btnValidar.setEnabled(false);

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos");

        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scaner = IntentIntegrator.forSupportFragment(CheckQRFragment.this);
                scaner.setPrompt("Escanea el QR del cliente");
                scaner.setOrientationLocked(false);
                scaner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                scaner.initiateScan();
            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (reservaRest.getCodigo().equals(codigdoReservaUsu)) {

                    dbRef.child("restaurantes").child(user.getUid()).child("reservas").child(reservaRest.getFecha()).
                            child(reservaRest.getHora()).child(reservaRest.getCodigo()).removeValue();

                    dbRef.child("usuarios").child(reservaRest.getUserUID()).child("reservas").child(reservaRest.getFecha()).
                            child(reservaRest.getCodigo()).removeValue();


                    listener.volverActivityReservasRest();

                } else {
                    Toast.makeText(getContext(), "No coicide", Toast.LENGTH_SHORT).show();
                }



            }
        });

        return view;
    }

    //Metodo para obtener el ActionBar del Activity
    private void getActionBar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            ActionBar actionBar = appCompatActivity.getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(R.string.title_qr_rest);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
            }

        }
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        addListener();
//    }

//    private void addListener() {
//        if (vel == null) {
//            vel = new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    ReservaUsu reservaUsu = dataSnapshot.getValue(ReservaUsu.class);
//                    reservaUsu.setCodigo(reservaRest.getCodigo());
//
//                    if (reservaUsu != null) {
//
//
//
//
//                    }
//
//                    Toast.makeText(getContext(), "La reserva no existe", Toast.LENGTH_SHORT).show();
//
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
//                }
//            };
//            dbRef.child("usuarios").child(reservaRest.getUserUID()).child("reservas").child(reservaRest.getFecha()).
//                    child(reservaRest.getCodigo()).addValueEventListener(vel);
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        removeListener();
//    }
//
//    private void removeListener() {
//        if (vel != null) {
//            dbRef.removeEventListener(vel);
//            vel = null;
//        }
//
//    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CheckQRListener) {
            listener = (CheckQRListener) context;
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