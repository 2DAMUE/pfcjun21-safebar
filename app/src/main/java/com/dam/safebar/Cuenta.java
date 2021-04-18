package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dam.safebar.fragments.AboutUs;
import com.dam.safebar.fragments.Ayuda;
import com.dam.safebar.fragments.Configuracion;
import com.dam.safebar.fragments.Protocolo_Covid;

public class Cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
    }

    public void Configuracion(View view) {
        Intent i =new Intent(this, Configuracion.class);
        startActivity(i);

    }

    public void AboutUs(View view) {
        Intent i =new Intent(this, AboutUs.class);
        startActivity(i);
    }

    public void Ayuda(View view) {
        Intent i =new Intent(this, Ayuda.class);
        startActivity(i);
    }

    public void Protocolo(View view) {
        Intent i =new Intent(this, Protocolo_Covid.class);
        startActivity(i);

    }
}