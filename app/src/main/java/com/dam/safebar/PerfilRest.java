package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dam.safebar.adapters.BottomNavigationHelperRest;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.EditarPerflRestFragment;
import com.dam.safebar.fragments.PerfilRestFragment;
import com.dam.safebar.listeners.PerfilRestListener;
import com.google.android.material.appbar.MaterialToolbar;

public class PerfilRest extends BottomNavigationHelperRest implements PerfilRestListener {


    MaterialToolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tb = findViewById(R.id.topAppBarPerfilRest);
        tb.setNavigationIcon(null);
        setSupportActionBar(tb);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PerfilRestFragment prf = new PerfilRestFragment().newInstance();
        ft.add(R.id.flPerfilRest, prf);
        ft.addToBackStack(null);
        ft.commit();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flPerfilRest);
        if (f instanceof PerfilRestFragment) {

        } else {
            tb.setNavigationIcon(null);
            tb.setTitle("Perfil");
            super.onBackPressed();
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_perfil_rest;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmPerfilRest;
    }


    @Override
    public void abrirEditCuenta() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        EditarPerflRestFragment eprf = new EditarPerflRestFragment().newInstance();
        ft.replace(R.id.flPerfilRest, eprf);
        ft.addToBackStack(null);
        ft.commit();

        tb.setTitle("Editar perfil");
        tb.setNavigationIcon(R.drawable.ic_back_arrow);

    }

    @Override
    public void volverPerfilRest() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PerfilRestFragment prf = new PerfilRestFragment().newInstance();
        ft.replace(R.id.flPerfilRest, prf);
        ft.addToBackStack(null);
        ft.commit();
        tb.setNavigationIcon(null);
        tb.setTitle("Perfil");

    }

    @Override
    public void salir() {
        Intent intent = new Intent(PerfilRest.this, Splash.class);
        startActivity(intent);
        finish();
    }
}