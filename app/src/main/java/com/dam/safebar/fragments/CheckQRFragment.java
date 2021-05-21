package com.dam.safebar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CheckQRListener;
import com.dam.safebar.listeners.InicioListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class CheckQRFragment extends Fragment {

    CheckQRListener listener;
    Button btnEscanear;
    TextView tvCodigo;
    ImageView imResult;


    public CheckQRFragment() {
        // Required empty public constructor
    }


    public CheckQRFragment newInstance() {
        CheckQRFragment fragment = new CheckQRFragment();
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                btnEscanear.setVisibility(View.GONE);
                imResult.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_check_100, null));
                String resultConcat = getResources().getString(R.string.reserva_verificada) + "\n ID: " + result.getContents();
                tvCodigo.setText(resultConcat);
                tvCodigo.setTextColor(getResources().getColor(R.color.green_light));

                //TODO: qr leido correctamente

            } else {
                tvCodigo.setText(getResources().getString(R.string.error_lectura_qr));
                tvCodigo.setTextColor(getResources().getColor(R.color.orange_dark));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_check_qr, container, false);
        imResult = view.findViewById(R.id.imgQRStatus);
        tvCodigo = view.findViewById(R.id.tvCodigo);

        getActionBar();

        btnEscanear = view.findViewById(R.id.btnEscanear);

        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scaner = IntentIntegrator.forSupportFragment(CheckQRFragment.this);
                scaner.setPrompt("Escanea el QR del cliente");
                scaner.setOrientationLocked(false);
                scaner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                scaner.initiateScan();
            }
        });






        return view;
    }

    //Metodo para obtener el ActionBar del Activity
    private void getActionBar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            ActionBar actionBar = appCompatActivity.getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(R.string.title_qr_rest);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
            }

        }
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof CheckQRListener) {
//            listener = (CheckQRListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }





}