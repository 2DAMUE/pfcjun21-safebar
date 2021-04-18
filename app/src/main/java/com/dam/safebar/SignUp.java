package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputLayout;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageView background = (ImageView) findViewById(R.id.ivBackgroundSignUp);
        Button btnSignup = (Button) findViewById(R.id.btnSignUp);
        TextInputLayout etNombre = (TextInputLayout) findViewById(R.id.lSignUpNombre);
        TextInputLayout etEmail = (TextInputLayout) findViewById(R.id.lSignUpEmail);
        TextInputLayout etPassword = (TextInputLayout) findViewById(R.id.lSignUpPassword);
        TextInputLayout etTelefono = (TextInputLayout) findViewById(R.id.lSignUpTelefono);

        Glide.with(this)
                .load(R.drawable.food_variant)
                .fitCenter()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(6, 1)))
                .into(background);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty(etNombre)) {

                    etNombre.setError(null);

                    if (checkEmpty(etEmail)) {

                        etEmail.setError(null);

                        if (checkEmpty(etPassword)) {

                            etPassword.setError(null);

                            //TODO: REGISTRAR USUARIO

                        } else { etPassword.setError("Campo obligatorio"); }
                    } else { etEmail.setError("Campo obligatorio"); }
                } else { etNombre.setError("Campo obligatorio"); }
            }
        });

    }

    //Metodos para optimizar el isEmpty y obtener el String
    private boolean checkEmpty(TextInputLayout et) {
        return !et.getEditText().getText().toString().isEmpty();
    }

    private String getInputString(TextInputLayout et) {
        return et.getEditText().getText().toString();
    }

    public void goLogIn(View view) {
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }
}