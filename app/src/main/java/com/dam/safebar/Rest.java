package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dam.safebar.fragments.BookingFragment;
import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.fragments.RestauranteFragment;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.ReservarListener;

public class Rest extends AppCompatActivity implements ReservarListener {

    String restUID;
    String restNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        restUID = getIntent().getStringExtra(Inicio.COD_REST_UID);
        restNom = getIntent().getStringExtra(Inicio.COD_REST_NOM);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RestauranteFragment restf = new RestauranteFragment().newInstance(restUID, restNom);
        ft.add(R.id.flRestaurante, restf);
        ft.addToBackStack(null);
        ft.commit();


    }


    @Override
    public void abrirReservar(String restUID, String restNom) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BookingFragment bf = new BookingFragment().newInstance(restUID, restNom);
        ft.replace(R.id.flRestaurante, bf);
        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void booking() {
        Intent i = new Intent(Rest.this, Inicio.class);
        startActivity(i);
        finish();
    }
}