package com.dam.safebar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.InicioListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Inicio extends BottomNavigationHelper implements InicioListener {

    public static final String COD_REST_UID = "restUID";
    public static final String COD_REST_NOM = "restNom";

    ArrayList<Restaurante> listaRestaurantes;
    Restaurante  restaurante;

    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;
    MaterialToolbar tb;

    @Override
    public int getContentViewId() {
        return R.layout.activity_inicio;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmInicio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("PARCERROR", "Inicio OnCreate");
//        @BottomNavigationHelper monta directamente el layout
//        setContentView(R.layout.activity_inicio);

        //TODO: problema Snackbar, intuyo que por savedInstanceState
        if (getIntent().getBooleanExtra(Rest.BOOKING_OK, false)) {
            Snackbar snackbar = Snackbar
                    .make(getWindow().getDecorView().getRootView(), R.string.reserva_realizada, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.green_dark));

            snackbar.setAction("VER", v -> {
                removeListener();
                startActivity(new Intent(Inicio.this, ReservasUsu.class));
            });

            snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            snackbar.setAnchorView(R.id.bottomNavigationBar);
            snackbar.show();
        }





//        if (getIntent().getStringExtra("SNACKBAR") != null) {
//            if (getIntent().getStringExtra("SNACKBAR").equals("SB")) {
//
//                Log.i("PARCERROR", "Rest booking()");
//            }
//        }

        tb = findViewById(R.id.topAppBarInicio);
        tb.setNavigationIcon(null);

        listaRestaurantes = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        addListener();

    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flInicio);
        if (f instanceof InicioFragment) {

        } else {
            super.onBackPressed();
        }
    }

    private void cargarInicioFragment() {
        Log.i("PARCERROR", "cargarInicioFragment()");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        InicioFragment if1 = new InicioFragment().newInstance(listaRestaurantes);
        ft.add(R.id.flInicio, if1);
        ft.addToBackStack(null);
        ft.commit();
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
                        restaurante = dss.getValue(Restaurante.class);
                        restaurante.setRestUID(dss.getKey());
                        listaRestaurantes.add(restaurante);
                    }

                    cargarInicioFragment();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar snackbar = Snackbar
                            .make(getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.bottomNavigationBar);
                    snackbar.show();
                }
            };
            dbRef.addValueEventListener(vel);
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
    public void abrirRestaurante(String restUID, String restNom) {

        removeListener();

        Intent i = new Intent(Inicio.this, Rest.class);
        i.putExtra(COD_REST_UID, restUID);
        i.putExtra(COD_REST_NOM, restNom);
        startActivity(i);
//        finish();
    }
}

