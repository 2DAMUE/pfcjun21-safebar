package com.dam.safebar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.AboutUsFragment;
import com.dam.safebar.fragments.AyudaFragment;
import com.dam.safebar.fragments.ConfiguracionFragment;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.EditarPerfilFragment;
import com.dam.safebar.fragments.ProtocoloCovidFragment;
import com.dam.safebar.listeners.CuentaListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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

        setSupportActionBar(tb);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        CuentaFragment cf = new CuentaFragment().newInstance();
        ft.add(R.id.flCuenta, cf);
        ft.addToBackStack(null);
        ft.commit();



        tb.setNavigationOnClickListener(v -> {
            onBackPressed();
//                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//                    tb.setNavigationIcon(null);
//                    tb.setTitle("Cuenta");
//                } else {
//                    if (true) {
//                        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flCuenta);
//                        if (f instanceof ConfiguracionFragment) {
//                            tb.setTitle("Configuraci??n");
//                        }
//                    }
//                    tb.setNavigationIcon(R.drawable.ic_back_arrow);
//                }
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

        tb.setTitle("Configuraci??n");
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
    public void llamar() {
        Uri telf = Uri.parse("tel:918047755");
        Intent llamada = new Intent(Intent.ACTION_DIAL, telf);

        if (llamada.resolveActivity(getPackageManager()) != null) {
            startActivity(llamada);
        } else {
            Snackbar snackbar = Snackbar
                    .make(getWindow().getDecorView().getRootView(), R.string.unexpected_error, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.orange_dark));
            snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            snackbar.setAnchorView(R.id.bottomNavigationBar);
            snackbar.show();

        }
    }

    @Override
    public void mandarCorreo(String email) {
        Intent it = new Intent(Intent.ACTION_SENDTO);
        it.setData(Uri.parse("mailto:" + email));
        startActivity(it);
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

    @Override
    public void onBackPressed() {
//        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flCuenta);
//        if (f instanceof CuentaFragment) {
//            tb.setNavigationIcon(null);
//            tb.setTitle("Cuenta");
//        } else if (f instanceof ConfiguracionFragment) {
//            super.onBackPressed();
//            tb.setTitle("Configuraci??n");
//        }
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flCuenta);
        if (f instanceof CuentaFragment) {

        } else {
            super.onBackPressed();
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

                tb.setNavigationIcon(null);
                tb.setTitle("Cuenta");
                Log.i("cuenta", "volviendo a cuenta..");
            } else {
                f = getSupportFragmentManager().findFragmentById(R.id.flCuenta);
                if (true) {

                    if (f instanceof ConfiguracionFragment) {
                        tb.setTitle("Configuraci??n");
                    }
                }
                tb.setNavigationIcon(R.drawable.ic_back_arrow);
            }
        }

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