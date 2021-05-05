package com.dam.safebar;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.ReservasUsuFragment;
import com.dam.safebar.javabeans.ReservaUsu;

import java.util.ArrayList;

public class ReservasUsu extends BottomNavigationHelper {
    ArrayList<ReservaUsu> listareservas;

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
        ReservaUsu r00 = new ReservaUsu();
        r00.setNombreRest("Mc");
        r00.setFecha("14 septiembre");
        r00.setHora("10:00");
        r00.setNumPersonas(4);

        listareservas = new ArrayList<>();
        listareservas.add(r00);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ReservasUsuFragment resvf = new ReservasUsuFragment().newInstance(listareservas);
        ft.add(R.id.flReservas, resvf);
        ft.addToBackStack(null);
        ft.commit();
    }
}