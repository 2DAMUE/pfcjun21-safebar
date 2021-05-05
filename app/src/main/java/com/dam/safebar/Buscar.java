package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.dam.safebar.adapters.BottomNavigationHelper;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.fragments.FiltrosFragment;

public class Buscar extends BottomNavigationHelper {

    @Override
    public int getContentViewId() {
        return R.layout.activity_buscar;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmBuscar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FiltrosFragment ff = new FiltrosFragment().newInstance();
        ft.add(R.id.flBuscar, ff);
        ft.addToBackStack(null);
        ft.commit();

    }
}