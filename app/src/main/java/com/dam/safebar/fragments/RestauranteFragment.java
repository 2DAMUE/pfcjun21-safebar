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
import com.dam.safebar.Inicio;
import com.dam.safebar.R;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.PerfilRestListener;
import com.dam.safebar.listeners.ReservarListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class RestauranteFragment extends Fragment {

    public static final String COD_REST_UID = "R1";

    Restaurante restaurante;
    String restUID;

    ReservarListener listener;

    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;

    ImageView img;
    TextView tvNom;
    TextView tvDirec;
    TextView tvCalif;
    TextView tvPrecio;
    TextView tvAforo;
    TextView tvDescrip;
    Button btnReservar;

    public RestauranteFragment() {
        // Required empty public constructor
    }

    public RestauranteFragment newInstance(String restUID) {
        RestauranteFragment fragment = new RestauranteFragment();
        Bundle args = new Bundle();
        args.putString(COD_REST_UID, restUID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restUID = getArguments().getString(COD_REST_UID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        img = view.findViewById(R.id.imgRestauranteFrag);
        tvNom = view.findViewById(R.id.tvNombreRestauranteFrag);
        tvDirec = view.findViewById(R.id.tvDirecRestauranteFrag);
        tvCalif = view.findViewById(R.id.tvCalifRestauranteFrag);
        tvPrecio = view.findViewById(R.id.tvPrecioRestauranteFrag);
        tvAforo = view.findViewById(R.id.tvAforoRestauranteFrag);
        tvDescrip = view.findViewById(R.id.tvDescripRestauranteFrag);
        btnReservar = view.findViewById(R.id.btnReservarRestauranteFrag);

        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        addListener();

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.reservar();

                //TODO: Firebase

            }
        });

        return view;
    }

    private void cargarDatos() {
        Glide.with(img)
                .load(restaurante.getUrlFoto())
                .placeholder(null)
                .into(img);

        tvNom.setText(restaurante.getNombreRest());
        tvDirec.setText(restaurante.getDireccion());
        tvCalif.setText(String.valueOf(restaurante.getCalificacion()));
        tvPrecio.setText(String.valueOf(restaurante.getPrecioMedio()));
        tvAforo.setText(String.valueOf(restaurante.getAforo()));
        tvDescrip.setText(restaurante.getDescripcion());
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

                    restaurante = dataSnapshot.getValue(Restaurante.class);
                    cargarDatos();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            };
            dbRef.child(restUID).addValueEventListener(vel);
        }
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
        if (context instanceof ReservarListener) {
            listener = (ReservarListener) context;
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