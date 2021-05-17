package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dam.safebar.fragments.BookingFragment;
import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.fragments.RestauranteFragment;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.ReservarListener;
import com.google.android.material.appbar.MaterialToolbar;

public class Rest extends AppCompatActivity implements ReservarListener {

    String restUID;
    String restNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        restUID = getIntent().getStringExtra(Inicio.COD_REST_UID);
        restNom = getIntent().getStringExtra(Inicio.COD_REST_NOM);

        MaterialToolbar tb = findViewById(R.id.topAppBar);
        tb.setTitle(restNom);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RestauranteFragment restf = new RestauranteFragment().newInstance(restUID, restNom);
        ft.add(R.id.flRestaurante, restf);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flRestaurante);
        if (f instanceof RestauranteFragment) {
            finish();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void abrirReservar(String restUID, String restNom) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BookingFragment bf = new BookingFragment().newInstance(restUID, restNom);
        ft.replace(R.id.flRestaurante, bf);
        ft.addToBackStack(null);
        ft.commit();

        //TODO: Firebase

    }

    @Override
    public void booking() {
        Intent i = new Intent(Rest.this, Inicio.class);
        startActivity(i);
    }
}