package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SignUp extends AppCompatActivity {

    ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        background = findViewById(R.id.ivBackgroundSignUp);

        Glide.with(this)
                .load(R.drawable.background_restaurante_crop)
                .fitCenter()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(20, 1)))
                .into(background);

    }

    public void goLogIn(View view) {
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }
}