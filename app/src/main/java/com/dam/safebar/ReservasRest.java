package com.dam.safebar;

import android.os.Bundle;

import com.dam.safebar.adapters.BottomNavigationHelperRest;

public class ReservasRest extends BottomNavigationHelperRest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_reservas_rest;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmReservasRest;
    }
}