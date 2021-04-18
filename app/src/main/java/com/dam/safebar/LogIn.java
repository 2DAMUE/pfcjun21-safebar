package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LogIn extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView background = (ImageView) findViewById(R.id.ivBackgroundLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        TextInputLayout etUsuarioEmail = (TextInputLayout) findViewById(R.id.lLoginUsuario);
        TextInputLayout etPassword = (TextInputLayout) findViewById(R.id.lLoginPassword);

        Glide.with(this)
                .load(R.drawable.food)
                .fitCenter()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(6, 1)))
                .into(background);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty(etUsuarioEmail)) {

                    etUsuarioEmail.setError(null);

                    if (checkEmpty(etPassword)) {

                        etPassword.setError(null);
                        Toast.makeText(LogIn.this, getInputString(etUsuarioEmail), Toast.LENGTH_SHORT).show();

                        //TODO: INICIAR SESION

                    } else { etPassword.setError("Obligatorio"); }

                } else { etUsuarioEmail.setError("Obligatorio"); }
            }
        });
    }

    private boolean checkEmpty(TextInputLayout et) {
        return !et.getEditText().getText().toString().isEmpty();
    }

    private String getInputString(TextInputLayout et) {
        return et.getEditText().getText().toString();
    }

    public void olvidasteContrasena(View view) {
    }

    public void goSignUp(View view) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }
}