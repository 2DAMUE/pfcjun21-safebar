package com.dam.safebar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dam.safebar.javabeans.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth fba;
    private FirebaseUser user;
    DatabaseReference dr;

    String nombre;
    String email;
    String password;
    String direccion;
    Usuario usuReg;

    TextInputLayout etNombre;
    TextInputLayout etEmail;
    TextInputLayout etPassword;
    TextInputLayout etDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageView background = findViewById(R.id.ivBackgroundSignUp);
        Button btnSignup = findViewById(R.id.btnSignUp);
        etNombre = findViewById(R.id.lSignUpNombre);
        etEmail = findViewById(R.id.lSignUpEmail);
        etPassword = findViewById(R.id.lSignUpPassword);
        etDireccion = findViewById(R.id.lSignUpDireccion);

        dr = FirebaseDatabase.getInstance().getReference("datos");
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        Glide.with(this)
                .load(R.drawable.food_variant)
                .fitCenter()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(6, 1)))
                .into(background);

        btnSignup.setOnClickListener(v -> {
            if (checkEmpty(etNombre)) {

                etNombre.setError(null);

                if (checkEmpty(etEmail)) {

                    etEmail.setError(null);

                    if (checkEmpty(etPassword)) {

                        etPassword.setError(null);

                        if (checkEmpty(etDireccion)) {

                            etDireccion.setError(null);

                            registrarUsuario();

                        }  else { etDireccion.setError("Campo obligatorio"); }
                    } else { etPassword.setError("Campo obligatorio"); }
                } else { etEmail.setError("Campo obligatorio"); }
            } else { etNombre.setError("Campo obligatorio"); }
        });

    }

    public void registrarUsuario() {

        nombre = etNombre.getEditText().getText().toString().trim();
        email = etEmail.getEditText().getText().toString().trim();
        password = etPassword.getEditText().getText().toString().trim();
        direccion = etDireccion.getEditText().getText().toString().trim();

        fba.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        user = fba.getCurrentUser();

                        usuReg = new Usuario(nombre, email, password, direccion);

                        dr.child("usuarios").child(user.getUid()).setValue(usuReg);

                        accederApp();

                        Toast.makeText(getApplicationContext(),"Registrado con ??xito!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext()," Ha ocurrido un error en el registro",
                                Toast.LENGTH_SHORT).show();
                    } });

    }

    private void accederApp() {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();
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