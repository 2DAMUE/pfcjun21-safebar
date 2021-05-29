package com.dam.safebar.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dam.safebar.Buscar;
import com.dam.safebar.Cuenta;
import com.dam.safebar.Inicio;
import com.dam.safebar.R;
import com.dam.safebar.ReservasUsu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BottomNavigationHelper extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        navigationView = findViewById(R.id.bottomNavigationBar);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.itmInicio) {
                Intent i = new Intent(this, Inicio.class);
                startActivity(i);
            } else if (itemId == R.id.itmBuscar) {
                Intent i = new Intent(this, Buscar.class);
                startActivity(i);
            } else if (itemId == R.id.itmReservas) {
                Intent i = new Intent(this, ReservasUsu.class);
                startActivity(i);
            } else if (itemId == R.id.itmCuenta) {
                Intent i = new Intent(this, Cuenta.class);
                startActivity(i);
            }
            finish();
        }, 300);
        //delayMillis original = 300
        return true;
    }

    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    public abstract int getContentViewId();

    public abstract int getNavigationMenuItemId();
}
