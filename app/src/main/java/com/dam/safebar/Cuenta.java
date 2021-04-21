package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.AboutUsFragment;
import com.dam.safebar.fragments.AyudaFragment;
import com.dam.safebar.fragments.ConfiguracionFragment;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.EditarPerfilFragment;
import com.dam.safebar.fragments.ProtocoloCovidFragment;
import com.dam.safebar.listeners.CuentaListener;

public class Cuenta extends BottomNavigationHelper implements CuentaListener {

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

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        CuentaFragment cf = new CuentaFragment().newInstance();
        ft.add(R.id.flCuenta, cf);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void abrirConfiguracion() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ConfiguracionFragment cff = new ConfiguracionFragment().newInstance();
        ft.replace(R.id.flCuenta, cff);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void abrirAboutUs() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AboutUsFragment au = new AboutUsFragment().newInstance();
        ft.replace(R.id.flCuenta, au);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void abrirAyuda() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AyudaFragment af = new AyudaFragment().newInstance();
        ft.replace(R.id.flCuenta, af);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void abrirProtocoloCovid() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ProtocoloCovidFragment pcf = new ProtocoloCovidFragment().newInstance();
        ft.replace(R.id.flCuenta, pcf);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void abrirEditPerf() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        EditarPerfilFragment epf = new EditarPerfilFragment().newInstance();
        ft.replace(R.id.flCuenta, epf);
        ft.addToBackStack(null);
        ft.commit();
    }


}