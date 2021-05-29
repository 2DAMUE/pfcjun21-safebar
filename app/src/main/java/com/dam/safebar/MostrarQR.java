package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dam.safebar.fragments.QRFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class MostrarQR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_qr);

        MaterialToolbar tb = findViewById(R.id.topAppBarQRUsu);
        tb.setNavigationOnClickListener(v -> onBackPressed());

        String codigoReserva = getIntent().getStringExtra("codQR");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        QRFragment qrf = new QRFragment().newInstance(codigoReserva);
        ft.add(R.id.flMostrarQR, qrf);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ReservasUsu.class));
    }




}