package com.dam.safebar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.InicioListener;
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
    ArrayList<Restaurante> listaRestaurantes;
    Restaurante  restaurante;

    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;

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
//        @BottomNavigationHelper monta directamente el layoutt
//        setContentView(R.layout.activity_inicio);

        listaRestaurantes = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        addListener();

    }

    private void cargarInicioFragment() {
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
                    Toast.makeText(Inicio.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
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
    public void abrirRestaurante(String restUID) {
        Intent i = new Intent(Inicio.this, Rest.class);
        i.putExtra(COD_REST_UID, restUID);
        startActivity(i);
        finish();
    }
}


//    Restaurante r0 = new Restaurante();
//    Restaurante r1 = new Restaurante();
//    Restaurante r2 = new Restaurante();
//    Restaurante r3 = new Restaurante();
//    Restaurante r4 = new Restaurante();
//    Restaurante r5 = new Restaurante();
//
//        r0.setNombreRest("Mc");
//                r1.setNombreRest("D");
//                r2.setNombreRest("G");
//                r3.setNombreRest("ASD");
//                r4.setNombreRest("jhjhj");
//                r5.setNombreRest("ZZZZZZ");
//
//                listaRestaurantes = new ArrayList<>();
//        listaRestaurantes.add(r0);
//        listaRestaurantes.add(r1);
//        listaRestaurantes.add(r2);
//        listaRestaurantes.add(r3);
//        listaRestaurantes.add(r4);
//        listaRestaurantes.add(r5);