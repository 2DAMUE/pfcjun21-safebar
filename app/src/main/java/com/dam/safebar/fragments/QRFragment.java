package com.dam.safebar.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dam.safebar.R;
import com.dam.safebar.listeners.CheckQRListener;
import com.dam.safebar.listeners.QRListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class QRFragment extends Fragment {

    QRListener listener;
    ImageView imageQR;
    String codigoReserva;

    public QRFragment() {
        // Required empty public constructor
    }


    public QRFragment newInstance(String codigoReserva) {
        QRFragment fragment = new QRFragment();
        Bundle args = new Bundle();
        args.putString("CODIGO_RESERVA", codigoReserva);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            codigoReserva = getArguments().getString("CODIGO_RESERVA");
        }

    }

    private void createQR(String codigo) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(codigo, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageQR.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qr, container, false);
        getActionBar();
        imageQR = view.findViewById(R.id.imQR);

        //BotonPrueba
        Button btnGenerar = view.findViewById(R.id.btnOnClicl);
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQR(codigoReserva);
            }
        });

        return view;
    }

    private void getActionBar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            ActionBar actionBar = appCompatActivity.getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(getResources().getString(R.string.title_qr_usu));
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);

            }

        }
    }



    //TODO: Ver que pasa

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof QRListener) {
//            listener = (QRListener) context;
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