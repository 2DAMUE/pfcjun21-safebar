package com.dam.safebar.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.PerfilRestListener;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class EditarPerflRestFragment extends Fragment {

    public static final int RC_PHOTO_ADJ = 1;

    FirebaseAuth fba;
    FirebaseUser user;

    DatabaseReference dbRef;
    ValueEventListener vel;
    Uri selectedUri;
    StorageReference mFotoStorageRef;

    Restaurante restLoged;
    Restaurante restEditado;
    PerfilRestListener listener;

    String nombreRest;
    String email;
    String password;
    String direc;
    String telef;
    String precio;
    String aforo;
    String descrip;
    boolean fotoCambiada;

    ImageView imageView;
    ImageButton imbEditarImagen;
    EditText etNombre;
    EditText etEmail;
    EditText etPW;
    EditText etDirec;
    EditText etTelef;
    EditText etPrecio;
    EditText etAforo;
    EditText etDescrip;

    public EditarPerflRestFragment() {
        // Required empty public constructor
    }


    public EditarPerflRestFragment newInstance() {
        EditarPerflRestFragment fragment = new EditarPerflRestFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editar_perfl_rest, container, false);

        //BTN GUARDAR EN ACTIONBAR
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar();


        imageView = view.findViewById(R.id.imgEditPerfRestFrag);
        imbEditarImagen = view.findViewById(R.id.imbEditPerfRestFrag);
        etNombre = view.findViewById(R.id.etEditPerfRestFragNom);
        etEmail = view.findViewById(R.id.etEditPerfRestFragEmail);
        etPW = view.findViewById(R.id.etEditPerfRestFragPW);
        etDirec = view.findViewById(R.id.etEditPerfRestFragDirec);
        etTelef = view.findViewById(R.id.etEditPerfRestFragTelef);
        etPrecio = view.findViewById(R.id.etEditPerfRestFragPrecio);
        etAforo = view.findViewById(R.id.etEditPerfRestFragAforo);
        etDescrip = view.findViewById(R.id.etEditPerfRestFragDescrip);


        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        fotoCambiada = false;

        addListener();

        imbEditarImagen.setOnClickListener(v -> {
            Toast.makeText(getContext(), R.string.selecciona_imagen, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            startActivityForResult(Intent.createChooser(intent, "Complete la acci??n usando"), RC_PHOTO_ADJ);
            fotoCambiada = true;

        });

        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.editar_perfil_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemGuardarPerfil) {


            nombreRest = etNombre.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            password = etPW.getText().toString().trim();
            direc = etDirec.getText().toString().trim();
            telef = etTelef.getText().toString().trim();
            precio = etPrecio.getText().toString().trim();
            aforo = etAforo.getText().toString().trim();
            descrip = etDescrip.getText().toString().trim();


            if (fotoCambiada) {
                final StorageReference fotoRef = mFotoStorageRef.child(selectedUri.getEncodedPath());
                UploadTask ut = fotoRef.putFile(selectedUri);
                Task<Uri> urlTask = ut.continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return fotoRef.getDownloadUrl();
                }).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        restEditado = new Restaurante(nombreRest, downloadUri.toString(), email, password, direc,
                                telef, Integer.parseInt(precio), Integer.parseInt(aforo), descrip);

                        user.updateEmail(email);
                        user.updatePassword(password);

                        //dbRef.child(user.getUid()).setValue(restEditado);
                        dbRef.child(user.getUid()).child("aforo").setValue(restEditado.getAforo());
                        dbRef.child(user.getUid()).child("descripcion").setValue(restEditado.getDescripcion());
                        dbRef.child(user.getUid()).child("direccion").setValue(restEditado.getDireccion());
                        dbRef.child(user.getUid()).child("email").setValue(restEditado.getEmail());
                        dbRef.child(user.getUid()).child("nombreRest").setValue(restEditado.getNombreRest());
                        dbRef.child(user.getUid()).child("password").setValue(restEditado.getPassword());
                        dbRef.child(user.getUid()).child("precioMedio").setValue(restEditado.getPrecioMedio());
                        dbRef.child(user.getUid()).child("telefono").setValue(restEditado.getTelefono());
                        dbRef.child(user.getUid()).child("urlFoto").setValue(downloadUri.toString());

                        Snackbar snackbar = Snackbar
                                .make(getActivity().getWindow().getDecorView().getRootView(), R.string.perfil_modificado_ok, Snackbar.LENGTH_LONG)
                                .setBackgroundTint(getResources().getColor(R.color.green_dark));
                        snackbar.setAnchorView(R.id.bottomNavigationBarRest);
                        snackbar.show();


//                            //View para introducir margen por encima del BottomBar
//                            View snackBarView = snackbar.getView();
//                            snackBarView.setTranslationY(-(convertDpToPixel(56, getActivity())));
//                            snackbar.show();

                        listener.volverPerfilRest();

                    }
                });
            } else {
                restEditado = new Restaurante(nombreRest, restLoged.getUrlFoto(), email, password, direc,
                        telef, Integer.parseInt(precio), Integer.parseInt(aforo), descrip);

                user.updateEmail(email);
                user.updatePassword(password);

                //dbRef.child(user.getUid()).setValue(restEditado);
                dbRef.child(user.getUid()).child("aforo").setValue(restEditado.getAforo());
                dbRef.child(user.getUid()).child("descripcion").setValue(restEditado.getDescripcion());
                dbRef.child(user.getUid()).child("direccion").setValue(restEditado.getDireccion());
                dbRef.child(user.getUid()).child("email").setValue(restEditado.getEmail());
                dbRef.child(user.getUid()).child("nombreRest").setValue(restEditado.getNombreRest());
                dbRef.child(user.getUid()).child("password").setValue(restEditado.getPassword());
                dbRef.child(user.getUid()).child("precioMedio").setValue(restEditado.getPrecioMedio());
                dbRef.child(user.getUid()).child("telefono").setValue(restEditado.getTelefono());
                dbRef.child(user.getUid()).child("urlFoto").setValue(restLoged.getUrlFoto());

                Snackbar snackbar = Snackbar
                        .make(getActivity().getWindow().getDecorView().getRootView(), R.string.perfil_modificado_ok, Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.green_dark));
                snackbar.setAnchorView(R.id.bottomNavigationBarRest);
                snackbar.show();


//                //View para introducir margen por encima del BottomBar
//                View snackBarView = snackbar.getView();
//                snackBarView.setTranslationY(-(convertDpToPixel(56, getActivity())));
//                snackbar.show();

                listener.volverPerfilRest();
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    public static float convertDpToPixel(float dp, Context context){
//        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_ADJ && resultCode == Activity.RESULT_OK) {
            selectedUri = data.getData();
            Glide.with(this).load(selectedUri).circleCrop()
                    .into(imageView);
        }
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        addListener();
//    }

    private void addListener() {
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    restLoged = dataSnapshot.getValue(Restaurante.class);

                    cargarDatosUsuario();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.bottomNavigationBarRest);
                    snackbar.show();
                }
            };
            dbRef.child(user.getUid()).addValueEventListener(vel);
        }
    }

    private void cargarDatosUsuario() {

        removeListener();
        if (getActivity() != null) {
            Fragment f = getActivity().getSupportFragmentManager().findFragmentById(R.id.flPerfilRest);
            if (f instanceof EditarPerflRestFragment) {
                Glide.with(this)
                        .load(restLoged.getUrlFoto())
                        .placeholder(null)
                        .circleCrop()
                        .into(imageView);
            }

        }


        etNombre.setText(restLoged.getNombreRest());
        etEmail.setText(restLoged.getEmail());
        etPW.setText(restLoged.getPassword());
        etDirec.setText(restLoged.getDireccion());
        etTelef.setText(restLoged.getTelefono());
        etPrecio.setText(String.valueOf(restLoged.getPrecioMedio()));
        etAforo.setText(String.valueOf(restLoged.getAforo()));
        etDescrip.setText(restLoged.getDescripcion());

    }

    @Override
    public void onPause() {
        super.onPause();
        removeListener();
    }

    private void removeListener() {
        if (vel != null) {
            dbRef.removeEventListener(vel);
            vel = null;
        }

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PerfilRestListener) {
            listener = (PerfilRestListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}