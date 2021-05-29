package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dam.safebar.adapters.BottomNavigationHelperRest;
import com.dam.safebar.fragments.CheckQRFragment;
import com.dam.safebar.fragments.CuentaFragment;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.listeners.CheckQRListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

public class CheckQR extends AppCompatActivity implements CheckQRListener {

    public final static String RES_VERIFICADA= "VERIF";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_qr);

        MaterialToolbar tb = findViewById(R.id.topAppBarQRRest);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String fechaReserva = getIntent().getStringExtra("fechaQR");
        String horaReserva = getIntent().getStringExtra("horaQR");
        String codigoReserva = getIntent().getStringExtra("codReservQR");
        String uidReserva = getIntent().getStringExtra("uidQR");

        ReservaRest reservaRest = new ReservaRest();
        reservaRest.setFecha(fechaReserva);
        reservaRest.setHora(horaReserva);
        reservaRest.setCodigo(codigoReserva);
        reservaRest.setUserUID(uidReserva);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        CheckQRFragment chqrf = new CheckQRFragment().newInstance(reservaRest);
        ft.add(R.id.flCheckQR, chqrf);
        ft.addToBackStack(null);
        ft.commit();



    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void volverActivityReservasRest() {

        Intent i = new Intent(this, ReservasRest.class);
        i.putExtra(RES_VERIFICADA, true);
        startActivity(i);

    }
}