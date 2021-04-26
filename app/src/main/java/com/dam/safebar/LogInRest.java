package com.dam.safebar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LogInRest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_rest);
        ImageView background = (ImageView) findViewById(R.id.ivBackgroundLoginRest);
        Button btnLogin = (Button) findViewById(R.id.btnLoginRest);
        TextInputLayout etUsuarioEmail = (TextInputLayout) findViewById(R.id.lLoginUsuarioRest);
        TextInputLayout etPassword = (TextInputLayout) findViewById(R.id.lLoginPasswordRest);
        SwitchMaterial swRest = (SwitchMaterial) findViewById(R.id.switchLoginRest);

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
                        Toast.makeText(LogInRest.this, getInputString(etUsuarioEmail), Toast.LENGTH_SHORT).show();

                        //TODO: INICIAR SESION

                    } else { etPassword.setError("Obligatorio"); }

                } else { etUsuarioEmail.setError("Obligatorio"); }

                Intent intent = new Intent(LogInRest.this, InicioRest.class);
                startActivity(intent);
            }
        });

        swRest.setChecked(true);
        swRest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    Intent i = new Intent(LogInRest.this, LogIn.class);
                    startActivity(i);
                }
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
        Intent i = new Intent(this, SignUpRest.class);
        startActivity(i);
    }
}