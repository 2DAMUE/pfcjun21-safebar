package com.dam.safebar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.adapters.InicioAdapter;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.FiltrosFragment;
import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.BuscarListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Buscar extends BottomNavigationHelper implements BuscarListener {

    public static final String COD_REST_UID_FILTROS = "restUID";
    public static final String COD_REST_NOM_FILTROS = "restNom";

    ArrayList<Restaurante> listaRestaurantes;
    Restaurante  restaurante;

    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;

    String nombreR;
    String nomRestFB;
    int tamNom;

//    MaterialToolbar tb;

    @Override
    public int getContentViewId() {
        return R.layout.activity_buscar;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmBuscar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        tb = findViewById(R.id.topAppBarInicio);
//        tb.setNavigationIcon(null);

        listaRestaurantes = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FiltrosFragment ff = new FiltrosFragment().newInstance(listaRestaurantes);
        ft.add(R.id.flBuscar, ff);
        ft.addToBackStack(null);
        ft.commit();

    }


//    @Override
//    public void onBackPressed() {
//        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flBuscar);
//        if (f instanceof InicioFragment) {
//
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public void buscarRestaurantes(String nombre) {

        listaRestaurantes.clear();
        nombreR = "";
        tamNom = 0;
        nombreR = nombre.toUpperCase();
        tamNom = nombreR.length();
        if (!nombreR.isEmpty()) {
            addListener();
        }
    }



    //    @Override
//    public void onResume() {
//        super.onResume();
//        addListener();
//    }

    private void addListener() {
        onPause();
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        restaurante = dss.getValue(Restaurante.class);
                        restaurante.setRestUID(dss.getKey());

                        nomRestFB = "";
                        nomRestFB = restaurante.getNombreRest().toUpperCase();

                        if (nombreR.equals(nomRestFB.substring(0,tamNom))) {

                            listaRestaurantes.add(restaurante);
                        }


                    }

                    recargarFiltrosFrag();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Buscar.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.addValueEventListener(vel);
        }
    }

    @Override
    public void abrirRestaurante(String restUID, String restNom) {

        Intent intent = new Intent(Buscar.this, Rest.class);
        intent.putExtra(COD_REST_UID_FILTROS, restUID);
        intent.putExtra(COD_REST_NOM_FILTROS, restNom);
        startActivity(intent);
    }

    private void recargarFiltrosFrag() {

        removeListener();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FiltrosFragment ff = new FiltrosFragment().newInstance(listaRestaurantes);
        ft.replace(R.id.flBuscar, ff);
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


}