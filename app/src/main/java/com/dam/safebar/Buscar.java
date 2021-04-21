package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dam.safebar.adapters.BottomNavigationHelper;

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
//        @BottomNavigationHelper monta directamente el layout
//        setContentView(R.layout.activity_buscar);

    }
}