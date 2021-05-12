package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.AboutUsFragment;
import com.dam.safebar.fragments.AyudaFragment;
import com.dam.safebar.fragments.ConfiguracionFragment;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.EditarPerfilFragment;
import com.dam.safebar.fragments.ProtocoloCovidFragment;
import com.dam.safebar.listeners.CuentaListener;
import com.google.android.material.appbar.MaterialToolbar;

public class Cuenta extends BottomNavigationHelper implements CuentaListener {

    MaterialToolbar tb;

    @Override
    public int getContentViewId() {
        return R.layout.activity_cuenta;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmCuenta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tb = findViewById(R.id.topAppBarCuenta);
        tb.setNavigationIcon(null);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        CuentaFragment cf = new CuentaFragment().newInstance();
        ft.add(R.id.flCuenta, cf);
        ft.addToBackStack(null);
        ft.commit();



        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    tb.setNavigationIcon(null);
                    tb.setTitle("Cuenta");
                } else {
                    if (true) {
                        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flCuenta);
                        if (f instanceof ConfiguracionFragment) {
                            tb.setTitle("Configuración");
                        }
                    }
                    tb.setNavigationIcon(R.drawable.ic_back_arrow);
                }
            }
        });

    }

    @Override
    public void abrirConfiguracion() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ConfiguracionFragment cff = new ConfiguracionFragment().newInstance();
        ft.replace(R.id.flCuenta, cff);
        ft.addToBackStack(null);
        ft.commit();

        tb.setTitle("Configuración");
        tb.setNavigationIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void abrirAboutUs() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AboutUsFragment au = new AboutUsFragment().newInstance();
        ft.replace(R.id.flCuenta, au);
        ft.addToBackStack(null);
        ft.commit();

        tb.setTitle("About Us");
        tb.setNavigationIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void abrirAyuda() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AyudaFragment af = new AyudaFragment().newInstance();
        ft.replace(R.id.flCuenta, af);
        ft.addToBackStack(null);
        ft.commit();

        tb.setTitle("Ayuda");
        tb.setNavigationIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void abrirProtocoloCovid() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ProtocoloCovidFragment pcf = new ProtocoloCovidFragment().newInstance();
        ft.replace(R.id.flCuenta, pcf);
        ft.addToBackStack(null);
        ft.commit();

        tb.setTitle("Protocolo Covid");
        tb.setNavigationIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void abrirEditPerf() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        EditarPerfilFragment epf = new EditarPerfilFragment().newInstance();
        ft.replace(R.id.flCuenta, epf);
        ft.addToBackStack(null);
        ft.commit();

        tb.setTitle("Editar perfil");
        tb.setNavigationIcon(R.drawable.ic_back_arrow);
    }

    @Override
    public void salir() {
        Intent intent = new Intent(Cuenta.this, Splash.class);
        startActivity(intent);
        finish();
    }

//    public void resetActionBar(boolean childAction, int drawerMode)
//    {
//        if (childAction) {
//            // [Undocumented?] trick to get up button icon to show
//            drawerToggle.setDrawerIndicatorEnabled(false);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        } else {
//            drawerToggle.setDrawerIndicatorEnabled(true);
//        }
//
//        drawerLayout.setDrawerLockMode(drawerMode);
//    }


}