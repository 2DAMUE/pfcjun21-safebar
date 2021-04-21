package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.InicioFragment;
import com.dam.safebar.javabeans.Restaurante;

import java.util.ArrayList;

public class Inicio extends BottomNavigationHelper {

    ArrayList<Restaurante> listaRestaurantes;

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

        Restaurante r0 = new Restaurante();
        Restaurante r1 = new Restaurante();
        Restaurante r2 = new Restaurante();
        Restaurante r3 = new Restaurante();
        Restaurante r4 = new Restaurante();
        Restaurante r5 = new Restaurante();

        r0.setNombreRest("Mc");
        r1.setNombreRest("D");
        r2.setNombreRest("G");
        r3.setNombreRest("ASD");
        r4.setNombreRest("jhjhj");
        r5.setNombreRest("ZZZZZZ");

        listaRestaurantes = new ArrayList<>();
        listaRestaurantes.add(r0);
        listaRestaurantes.add(r1);
        listaRestaurantes.add(r2);
        listaRestaurantes.add(r3);
        listaRestaurantes.add(r4);
        listaRestaurantes.add(r5);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        InicioFragment if1 = new InicioFragment().newInstance(listaRestaurantes);
        ft.add(R.id.flInicio, if1);
        ft.addToBackStack(null);
        ft.commit();

    }
}