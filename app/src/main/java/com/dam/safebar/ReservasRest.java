package com.dam.safebar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dam.safebar.adapters.BottomNavigationHelperRest;
import com.dam.safebar.fragments.ReservasRestFragment;
import com.dam.safebar.fragments.ReservasUsuFragment;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.listeners.ReservasRestListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReservasRest extends BottomNavigationHelperRest implements ReservasRestListener {

    ArrayList<ReservaRest> listaReservas;

    ReservaRest reservaRest;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    ValueEventListener vel;

    String fecha;
    ArrayList<String> listaFechas;
    String hora;
    ArrayList<String> listaHoras;

    String fechaActual;
    String horaActual;

    @Override
    public int getContentViewId() {
        return R.layout.activity_reservas_rest;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmReservasRest;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        listaReservas = new ArrayList<ReservaRest>();
        listaFechas = new ArrayList<String>();
        listaHoras = new ArrayList<String>();

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");

        addListener();


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
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        fecha = dss.getKey();
                        listaFechas.add(fecha);

                    }

                    if (listaFechas != null) {
                        for (String fechaArray: listaFechas) {
                            fechaActual = fechaArray;
                            addListener2();
                        }
                        cargarReservasRestFragment();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasRest.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").addValueEventListener(vel);
        }
    }

    private void addListener2() {

        onPause();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        hora = dss.getKey();
                        listaHoras.add(hora);

                    }

                    if (listaHoras != null) {
                        for (String horaArray: listaHoras) {
                            horaActual = horaArray;
                            addListener3();
                        }

                    }

                    onPause();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasRest.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").child(fechaActual).addValueEventListener(vel);
        }
    }

    private void addListener3() {

        onPause();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        reservaRest = dss.getValue(ReservaRest.class);
                        reservaRest.setCodigo(dss.getKey());

                        listaReservas.add(reservaRest);


                    }

                    onPause();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasRest.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").child(fechaActual).child(horaActual).addValueEventListener(vel);
        }




    }

    private void cargarReservasRestFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ReservasRestFragment resvf = new ReservasRestFragment().newInstance(listaReservas);
        ft.add(R.id.flReservasRest, resvf);
        ft.addToBackStack(null);
        ft.commit();
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
    public void abrirFragmentCheckQR(String codigo) {

        //TODO: abrir fragment para comprobar QR

    }
}



