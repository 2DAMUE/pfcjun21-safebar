package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dam.safebar.adapters.BottomNavigationHelper;

public class Reservas extends BottomNavigationHelper {

    @Override
    public int getContentViewId() {
        return R.layout.activity_reservas;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.itmReservas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}