package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.EditarPerflRestFragment;
import com.dam.safebar.fragments.PerfilRestFragment;
import com.dam.safebar.listeners.PerfilRestListener;

public class PerfilRest extends AppCompatActivity implements PerfilRestListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_rest);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PerfilRestFragment prf = new PerfilRestFragment().newInstance();
        ft.add(R.id.flPerfilRest, prf);
        ft.addToBackStack(null);
        ft.commit();


    }


    @Override
    public void abrirEditCuenta() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        EditarPerflRestFragment eprf = new EditarPerflRestFragment().newInstance();
        ft.replace(R.id.flPerfilRest, eprf);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void volverPerfilRest() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PerfilRestFragment prf = new PerfilRestFragment().newInstance();
        ft.replace(R.id.flPerfilRest, prf);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void salir() {
        Intent intent = new Intent(PerfilRest.this, Splash.class);
        startActivity(intent);
        finish();
    }
}