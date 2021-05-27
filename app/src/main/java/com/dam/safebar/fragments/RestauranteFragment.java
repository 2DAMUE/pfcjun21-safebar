package com.dam.safebar.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.listeners.PerfilRestListener;
import com.dam.safebar.listeners.ReservarListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class RestauranteFragment extends Fragment {

    public static final String COD_REST_UID = "R1";
    public static final String COD_REST_NOM = "R2";

    Restaurante restaurante;
    String restUID;
    String restNom;

    ReservarListener listener;

    DatabaseReference dbRef;
    StorageReference mFotoStorageRef;
    ValueEventListener vel;

    ImageView img;
    TextView tvNom;
    TextView tvDirec;
    TextView tvPrecio;
    TextView tvAforo;
    TextView tvDescrip;
    Button btnIndicaciones;
    Button btnLlamar;
    Button btnReservar;
    RatingBar rtbRestaurante;

    public RestauranteFragment() {
        // Required empty public constructor
    }

    public RestauranteFragment newInstance(String restUID, String restNom) {
        RestauranteFragment fragment = new RestauranteFragment();
        Bundle args = new Bundle();
        args.putString(COD_REST_UID, restUID);
        args.putString(COD_REST_NOM, restNom);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restUID = getArguments().getString(COD_REST_UID);
            restNom = getArguments().getString(COD_REST_NOM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        img = view.findViewById(R.id.imgRestauranteFrag);
        tvNom = view.findViewById(R.id.tvNombreRestauranteFrag);
        tvDirec = view.findViewById(R.id.tvDirecRestauranteFrag);
        tvPrecio = view.findViewById(R.id.tvPrecioRestauranteFrag);
        tvAforo = view.findViewById(R.id.tvAforoRestauranteFrag);
        tvDescrip = view.findViewById(R.id.tvDescripRestauranteFrag);

        btnReservar = view.findViewById(R.id.btnRestReservar);

        rtbRestaurante = view.findViewById(R.id.ratingBarRestaurante);
        btnLlamar = view.findViewById(R.id.btnLlamarRestauranteFrag);
        //btnNavegar = view.findViewById(R.id.btnNavegarRestauranteFrag);
        btnIndicaciones = view.findViewById(R.id.btnNavegarRestauranteFrag);

        dbRef = FirebaseDatabase.getInstance().getReference("datos/restaurantes");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("fotosR");

        addListener();

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.abrirReservar(restUID, restNom);
            }
        });

        btnIndicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add = restaurante.getDireccion();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + add);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.llamar(restaurante.getTelefono());

            }
        });


        return view;
    }

    private void cargarDatos() {

        removeListener();

        Glide.with(img)
                .load(restaurante.getUrlFoto())
                .placeholder(null)
                .into(img);

        tvNom.setText(restaurante.getNombreRest());
        tvDirec.setText(restaurante.getDireccion());
        tvPrecio.setText(String.valueOf(restaurante.getPrecioMedio()));
        tvAforo.setText(String.valueOf(restaurante.getAforo()));
        tvDescrip.setText(restaurante.getDescripcion());
        rtbRestaurante.setRating(restaurante.getCalificacion());
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
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.btnRestReservar);
                    snackbar.show();
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