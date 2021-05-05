package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.fragments.RestauranteFragment;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.ReservarListener;

public class Rest extends AppCompatActivity implements ReservarListener {

    Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        restaurante = getIntent().getParcelableExtra(Inicio.COD_REST);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RestauranteFragment restf = new RestauranteFragment().newInstance(restaurante);
        ft.add(R.id.flRestaurante, restf);
        ft.addToBackStack(null);
        ft.commit();

    }


    @Override
    public void reservar() {

        //TODO: Firebase

    }
}