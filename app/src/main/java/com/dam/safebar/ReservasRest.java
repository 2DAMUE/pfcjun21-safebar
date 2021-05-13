package com.dam.safebar;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dam.safebar.adapters.BottomNavigationHelperRest;
import com.dam.safebar.fragments.ReservasRestFragment;
import com.dam.safebar.fragments.ReservasUsuFragment;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;

import java.util.ArrayList;

public class ReservasRest extends BottomNavigationHelperRest {
    ArrayList<ReservaRest> listareservas;



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
        ReservaRest r0 = new ReservaRest();
        r0.setUserUID("Francisco");
        r0.setFecha("11 septiembre");
        r0.setHora("11:00");
        r0.setNumPersonas(100);


        listareservas = new ArrayList<>();
        listareservas.add(r0);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ReservasRestFragment resvf = new ReservasRestFragment().newInstance(listareservas);
        ft.add(R.id.flReservasRest, resvf);
        ft.addToBackStack(null);
        ft.commit();
    }
}



