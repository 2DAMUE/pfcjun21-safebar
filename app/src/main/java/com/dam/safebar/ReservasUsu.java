package com.dam.safebar;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.ReservasUsuFragment;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.ReservasUsuListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ReservasUsu extends BottomNavigationHelper implements ReservasUsuListener {

    ArrayList<ReservaUsu> listaReservas;
    ReservaUsu reservaUsu;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    ValueEventListener vel;

    String fecha;
    ArrayList<String> listaFechas;

    @Override
    public int getContentViewId() {
        return R.layout.activity_reservas_usu;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmReservas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: MaterialToolbar

        listaReservas = new ArrayList<ReservaUsu>();
        listaFechas = new ArrayList<String>();

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos/usuarios");

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
                            addListener2(fechaArray);
                        }
                        cargarReservasUsuFragment();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasUsu.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").addValueEventListener(vel);
        }
    }

    private void addListener2(String fechaArray) {

        onPause();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        reservaUsu = dss.getValue(ReservaUsu.class);
                        reservaUsu.setCodigo(dss.getKey());

                        listaReservas.add(reservaUsu);
                    }

                    onPause();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ReservasUsu.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).child("reservas").child(fechaArray).addValueEventListener(vel);
        }




    }

    private void cargarReservasUsuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ReservasUsuFragment resvf = new ReservasUsuFragment().newInstance(listaReservas);
        ft.add(R.id.flReservas, resvf);
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
    public void abrirFragmentQR(String codigo) {

        //TODO: Abrir qrFragment


    }
}

