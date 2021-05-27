package com.dam.safebar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    ImageView logo;
    TextView nombre;
    SharedPreferences loginDataCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginDataCheck = getSharedPreferences("loginData", Context.MODE_PRIVATE);

        logo = findViewById(R.id.ivLogoChefSplash);
        nombre = findViewById(R.id.tvNombreApp);

        Glide.with(this)
                .load(R.drawable.ic_logo)
                .fitCenter()
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.orange_dark)))
                .into(logo);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        logo.startAnimation(fadeIn);
        nombre.startAnimation(fadeIn);

        boolean rememberMe = loginDataCheck.getBoolean("REMEMBER_ME", false);

        if (rememberMe) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                openDelay(false);
            }
        } else {
            openDelay(true);
        }

    }

    private void openDelay(boolean b) {
        Handler handler = new Handler();
        if (b) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash
                            .this, LogIn.class);
                    startActivity(intent);
                    finish();
                }
            }, 3500);

        } else {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash
                            .this, Inicio.class);
                    startActivity(intent);
                    finish();
                }
            }, 3500);

        }
    }


}