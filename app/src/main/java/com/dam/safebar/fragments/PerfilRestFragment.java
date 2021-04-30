package com.dam.safebar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.PerfilRestListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class PerfilRestFragment extends Fragment {

    PerfilRestListener listener;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;

    Restaurante restLoged;

    ImageView img;
    TextView tvNom;
    TextView tvEmail;
    TextView tvDirec;
    TextView tvTelef;
    TextView tvPrecio;
    TextView tvAforo;
    TextView tvDescrip;
    Button btnEC;
    Button btnLO;

    public PerfilRestFragment() {
        // Required empty public constructor
    }


    public PerfilRestFragment newInstance() {
        PerfilRestFragment fragment = new PerfilRestFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil_rest, container, false);

        img = view.findViewById(R.id.imgPerfRestFrag);
        tvNom = view.findViewById(R.id.tvNombrePerfRestFrag);
        tvEmail = view.findViewById(R.id.tvEmailPerfRestFrag);
        tvDirec = view.findViewById(R.id.tvDirecPerfRestFrag);
        tvTelef = view.findViewById(R.id.tvTelefPerfRestFrag);
        tvPrecio = view.findViewById(R.id.tvPrecioPerfRestFrag);
        tvAforo = view.findViewById(R.id.tvAforoPerfRestFrag);
        tvDescrip = view.findViewById(R.id.tvDescripPerfRestFrag);
        btnEC = view.findViewById(R.id.btnEditCuentaPerfRestFrag);
        btnLO = view.findViewById(R.id.btnLogOutPerfRestFrag);

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        btnEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.abrirEditCuenta();

            }
        });

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
    public void onResume() {
        super.onResume();
        addListener();
    }

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
                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(user.getUid()).addValueEventListener(vel);
        }
    }

    private void cargarDatosUsuario() {
        Glide.with(img)
                .load(restLoged.getUrlFoto())
                .placeholder(R.drawable.usuario_1)
                .circleCrop()
                .into(img);

        tvNom.setText(restLoged.getNombreRest());
        tvEmail.setText(restLoged.getEmail());
        tvDirec.setText(restLoged.getDireccion());
        tvTelef.setText(restLoged.getTelefono());
        tvPrecio.setText(String.valueOf(restLoged.getPrecioMedio()));
        tvAforo.setText(String.valueOf(restLoged.getAforo()));
        tvDescrip.setText(restLoged.getDescripcion());


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