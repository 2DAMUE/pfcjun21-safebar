package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputLayout;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SignUpRest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_rest);

        ImageView background = (ImageView) findViewById(R.id.ivBackgroundSignUpRest);
        Button btnSignup = (Button) findViewById(R.id.btnSignUpRest);
        TextInputLayout etNombre = (TextInputLayout) findViewById(R.id.lSignUpNombreRest);
        TextInputLayout etEmail = (TextInputLayout) findViewById(R.id.lSignUpEmailRest);
        TextInputLayout etPassword = (TextInputLayout) findViewById(R.id.lSignUpPasswordRest);
        TextInputLayout etTelefono = (TextInputLayout) findViewById(R.id.lSignUpTelefonoRest);

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
        Intent i = new Intent(this, LogInRest.class);
        startActivity(i);
    }
}