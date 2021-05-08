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
import android.widget.Toast;

import com.dam.safebar.R;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.ReservarListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;


public class BookingFragment extends Fragment {

    public static final String COD_REST_UID_BOOKING = "RUID";
    public static final String COD_REST_NOM_BOOKING = "RNOM";

    String restUID;
    String restNom;
    String codigoReserva;
    ReservaUsu reservaUsu;
    ReservaRest reservaRest;

    ReservarListener listener;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    ValueEventListener vel;

    String fecha;
    String hora;
    String sNumPersonas;
    int numPersonas;

    EditText etFecha;
    EditText etHora;
    EditText etNumPersonas;
    Button btnReservar;

    public BookingFragment() {
        // Required empty public constructor
    }

    public BookingFragment newInstance(String restUID, String restNom) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(COD_REST_UID_BOOKING, restUID);
        args.putString(COD_REST_NOM_BOOKING, restNom);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restUID = getArguments().getString(COD_REST_UID_BOOKING);
            restNom = getArguments().getString(COD_REST_NOM_BOOKING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        etFecha = view.findViewById(R.id.etBookingFragFecha);
        etHora = view.findViewById(R.id.etBookingFragHora);
        etNumPersonas = view.findViewById(R.id.etBookingFragNumPers);
        btnReservar = view.findViewById(R.id.btnBookingFragReservar);

        dbRef = FirebaseDatabase.getInstance().getReference("datos");
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();



        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fecha = etFecha.getText().toString().trim();
                hora = etHora.getText().toString().trim();
                sNumPersonas = etNumPersonas.getText().toString().trim();

                if (fecha.isEmpty() || hora.isEmpty() || sNumPersonas.isEmpty()) {
                    Toast.makeText(getContext(), "Faltan datos!", Toast.LENGTH_SHORT).show();
                } else {
                    numPersonas = Integer.parseInt(sNumPersonas);
                    codigoReserva = "cod12ab";

                    reservaUsu = new ReservaUsu(restNom, numPersonas, codigoReserva);
                    reservaRest = new ReservaRest(user.getUid(), numPersonas, codigoReserva);

                    dbRef.child("usuarios").child(user.getUid()).child("reservas").child(fecha).child(hora).setValue(reservaUsu);
                    dbRef.child("restaurantes").child(restUID).child("reservas").child(fecha).child(hora).setValue(reservaRest);

                }



                listener.booking();

            }
        });



        return view;
    }


    //    @Override
//    public void onResume() {
//        super.onResume();
//        addListener();
//    }
//
//    private void addListener() {
//        if (vel == null) {
//            vel = new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    restaurante = dataSnapshot.getValue(Restaurante.class);
//                    cargarDatos();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
//                }
//            };
//            dbRef.child(restUID).addValueEventListener(vel);
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
        if (context instanceof ReservarListener) {
            listener = (ReservarListener) context;
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