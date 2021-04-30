package com.dam.safebar;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LogInRest extends AppCompatActivity {

    private FirebaseAuth fba;
    private FirebaseUser user;

    String email;
    String password;

    TextInputLayout etUsuarioEmail;
    TextInputLayout etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_rest);
        ImageView background = (ImageView) findViewById(R.id.ivBackgroundLoginRest);
        Button btnLogin = (Button) findViewById(R.id.btnLoginRest);
        etUsuarioEmail = (TextInputLayout) findViewById(R.id.lLoginUsuarioRest);
        etPassword = (TextInputLayout) findViewById(R.id.lLoginPasswordRest);
        SwitchMaterial swRest = (SwitchMaterial) findViewById(R.id.switchLoginRest);

        Glide.with(this)
                .load(R.drawable.food)
                .fitCenter()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(6, 1)))
                .into(background);

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty(etUsuarioEmail)) {

                    etUsuarioEmail.setError(null);

                    if (checkEmpty(etPassword)) {

                        etPassword.setError(null);

                        comprobarUsuario();

                    } else { etPassword.setError("Obligatorio"); }

                } else { etUsuarioEmail.setError("Obligatorio"); }

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

    private void comprobarUsuario() {

        email = etUsuarioEmail.getEditText().getText().toString().trim();
        password = etPassword.getEditText().getText().toString().trim();

        fba.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = fba.getCurrentUser();
                            acceder();

                        } else {
                            Toast.makeText(LogInRest.this, "El usuario introducido no existe",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });

    }

    private void acceder() {
        Intent intent = new Intent(LogInRest.this, PerfilRest.class);
        startActivity(intent);
        finish();
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