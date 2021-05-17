package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.javabeans.Usuario;
import com.dam.safebar.listeners.CuentaListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.zip.Inflater;

public class ConfiguracionFragment extends Fragment {

    CuentaListener listener;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;

    Usuario usuLoged;

    ImageView img;
    TextView tvNom;
    TextView tvEmail;
    TextView tvDirec;
    Button btnLO;

    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    public ConfiguracionFragment newInstance() {
        ConfiguracionFragment fragment = new ConfiguracionFragment();
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
        View view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        //BTN EDITAR
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar();

        img = view.findViewById(R.id.imgConfigFrag);
        tvNom = view.findViewById(R.id.tvNombreConfigFrag);
        tvEmail = view.findViewById(R.id.tvEmailConfigFrag);
        tvDirec = view.findViewById(R.id.tvDirecConfigFrag);
        btnLO = view.findViewById(R.id.btnLogOutConfigFrag);


        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos/usuarios");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotos");

//        btnEP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.abrirEditPerf();
//            }
//        });

        btnLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fba.signOut();
                listener.salir();

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.configuracion_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemEditarConfiguracion) {
            listener.abrirEditPerf();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        addListener();
    }

    private void addListener() {
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    usuLoged = dataSnapshot.getValue(Usuario.class);
                    cargarDatosUsuario();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).addValueEventListener(vel);
        }
    }

    private void cargarDatosUsuario() {
        Glide.with(this)
                .load(usuLoged.getUrlFoto())
                .placeholder(null)
                .circleCrop()
                .into(img);

        tvNom.setText(usuLoged.getNombre());
        tvEmail.setText(usuLoged.getEmail());
        tvDirec.setText(usuLoged.getDireccion());


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
        if (context instanceof CuentaListener) {
            listener = (CuentaListener) context;
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