package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LogIn extends AppCompatActivity {

    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        background = findViewById(R.id.ivBackgroundLogin);

        Glide.with(this)
                .load(R.drawable.background_restaurante_crop)
                .fitCenter()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(20, 1)))
                .into(background);
    }

    public void olvidasteContrasena(View view) {
    }

    public void goSignUp(View view) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }
}