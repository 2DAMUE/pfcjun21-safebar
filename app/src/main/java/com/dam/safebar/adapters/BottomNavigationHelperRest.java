package com.dam.safebar.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dam.safebar.PerfilRest;
import com.dam.safebar.R;
import com.dam.safebar.ReservasRest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BottomNavigationHelperRest extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBarRest);
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
            //TODO ITEMS REST
            if (itemId == R.id.itmReservasRest) {
                Intent i = new Intent(this, ReservasRest.class);
                startActivity(i);
            } else if (itemId == R.id.itmPerfilRest) {
                Intent i = new Intent(this, PerfilRest.class);
                startActivity(i);
            }
            finish();
        }, 300);
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
