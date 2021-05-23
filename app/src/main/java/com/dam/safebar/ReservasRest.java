package com.dam.safebar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dam.safebar.adapters.BottomNavigationHelperRest;
import com.dam.safebar.fragments.CheckQRFragment;
import com.dam.safebar.fragments.ReservasRestFragment;
import com.dam.safebar.fragments.ReservasUsuFragment;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.listeners.CheckQRListener;
import com.dam.safebar.listeners.ReservasRestListener;
import com.google.android.material.appbar.MaterialToolbar;
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
    int finalConsulta;
    MaterialToolbar tb;

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



        //AppBar
        tb = findViewById(R.id.topAppbarReservasRest);
        tb.setNavigationIcon(null);

        setSupportActionBar(tb);


        listaReservas = new ArrayList<ReservaRest>();
        listaFechas = new ArrayList<String>();
        listaHoras = new ArrayList<String>();

        reservaRest = new ReservaRest();

        finalConsulta = 0;

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");

        addListener();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ReservasRest.class));
//        super.onBackPressed();
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

                    if (listaFechas.size() != 0) {

                        for (int i=0; i<= listaFechas.size()-1; i++) {
                            //Log.i("ERROR1 FECHA", listaFechas.get(i));

                            if (i == listaFechas.size()-1) {
                                addListener2(listaFechas.get(i),1);
                            } else {
                                addListener2(listaFechas.get(i),2);
                            }

                        }

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

    private void addListener2(String fechaArray, int codFecha) {

        removeListener();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    listaHoras = null;
                    listaHoras = new ArrayList<String>();

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        hora = dss.getKey();
                        listaHoras.add(hora);

                    }

                    if (listaHoras.size() != 0) {

                        for (int i=0; i<= listaHoras.size()-1; i++) {

                            if (i == listaHoras.size()-1) {
                                addListener3(fechaArray, listaHoras.get(i), codFecha,1);
                            } else {
                                addListener3(fechaArray, listaHoras.get(i), codFecha, 2);
                            }


                        }


                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasRest.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").child(fechaArray).addValueEventListener(vel);
        }
    }

    private void addListener3(String fechaArray, String horaArray, int codFecha, int codHora) {


        removeListener();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //Log.i("ERROR3 FECHA", fechaArray);
                    Log.i("ERROR2 HORA", horaArray + " " + fechaArray);

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {

                        //reservaRest = dss.getValue(ReservaRest.class);

                        reservaRest = null;
                        reservaRest = new ReservaRest();

                        reservaRest.setFecha(dss.getValue(ReservaRest.class).getFecha());
                        Log.i("ERROR", reservaRest.getFecha());
                        reservaRest.setHora(dss.getValue(ReservaRest.class).getHora());
                        Log.i("ERROR", reservaRest.getHora());
                        reservaRest.setUserUID(dss.getValue(ReservaRest.class).getUserUID());
                        Log.i("ERROR", reservaRest.getUserUID());
                        reservaRest.setNomUsu(dss.getValue(ReservaRest.class).getNomUsu());
                        Log.i("ERROR", reservaRest.getNomUsu());
                        reservaRest.setNumPersonas(dss.getValue(ReservaRest.class).getNumPersonas());
                        Log.i("ERROR", String.valueOf(reservaRest.getNumPersonas()));
                        reservaRest.setCodigo(dss.getKey());
                        Log.i("ERROR", reservaRest.getCodigo());

                        listaReservas.add(reservaRest);
                        Log.i("ERROR LISTA", String.valueOf(listaReservas.size()));

                    }

                    removeListener();

                    if (codFecha == 1 && codHora == 1) {
                        ArrayList<ReservaRest> listaReservasFrag = listaReservas;

                        cargarReservasRestFragment(listaReservasFrag);
                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasRest.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").child(fechaArray).child(horaArray).addValueEventListener(vel);
        }

    }



    private void cargarReservasRestFragment(ArrayList<ReservaRest> listaReservasFrag) {

        removeListener();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ReservasRestFragment resvf = new ReservasRestFragment().newInstance(listaReservasFrag);
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
    public void abrirActivityCheckQR(ReservaRest reservaRest) {

        removeListener();

        Intent i = new Intent(ReservasRest.this, CheckQR.class);
        i.putExtra("fechaQR", reservaRest.getFecha());
        i.putExtra("horaQR", reservaRest.getHora());
        i.putExtra("codReservQR", reservaRest.getCodigo());
        i.putExtra("uidQR", reservaRest.getUserUID());
        startActivity(i);

    }

}



